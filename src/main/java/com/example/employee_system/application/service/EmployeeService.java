package com.example.employee_system.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.employee_system.domain.model.Employee;
import com.example.employee_system.domain.model.EmployeeSearchCondition;
import com.example.employee_system.domain.model.EmployeeSummary;
import com.example.employee_system.domain.model.User;
import com.example.employee_system.domain.repository.EmployeeRepository;
import com.example.employee_system.domain.repository.UserRepository;
import com.example.employee_system.presentation.request.EmployeeCreateRequest;
import com.example.employee_system.presentation.request.EmployeeSearchRequest;
import com.example.employee_system.presentation.response.EmployeeSearchResponse;
import com.example.employee_system.presentation.response.EmployeeSummaryResponse;

@Service
public class EmployeeService {

    /** 1ページに表示する件数 */
    private static final int PAGE_SIZE = 10;

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            UserRepository userRepository) {

        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    /**
     * 従業員を検索する。
     *
     * @param request 画面から受け取った検索条件
     * @return 従業員一覧とページング情報
     */
    public EmployeeSearchResponse searchEmployees(
            EmployeeSearchRequest request) {

        // 画面入力をDomain層の検索条件へ変換する
        EmployeeSearchCondition condition =
                toSearchCondition(request);

        // 検索条件に一致する総件数を取得する
        long totalCount =
                employeeRepository.countEmployees(condition);

        // 総ページ数を計算する
        int totalPages =
                calculateTotalPages(totalCount);

        // 現在のページ番号を決定する
        int currentPage =
                normalizePage(
                        request.getPage(),
                        totalPages);

        // DBの取得開始位置を計算する
        int offset =
                (currentPage - 1) * PAGE_SIZE;

        // 現在のページに表示する従業員を取得する
        List<EmployeeSummary> employees =
                employeeRepository.searchEmployees(
                        condition,
                        offset,
                        PAGE_SIZE);

        // Domain Modelを画面表示用Responseへ変換する
        List<EmployeeSummaryResponse> employeeResponses =
                employees.stream()
                        .map(this::toEmployeeSummaryResponse)
                        .toList();

        // 一覧とページング情報をまとめる
        EmployeeSearchResponse response =
                new EmployeeSearchResponse();

        response.setEmployees(employeeResponses);
        response.setCurrentPage(currentPage);
        response.setPageSize(PAGE_SIZE);
        response.setTotalCount(totalCount);
        response.setTotalPages(totalPages);

        return response;
    }

    /**
     * 登録画面の担当営業候補を取得する。
     */
    public List<User> getActiveSalesUsers() {
        return userRepository.findActiveSalesUsers();
    }

    /**
     * 社員番号が既に登録されているか確認する。
     */
    public boolean existsByEmployeeNo(
            String employeeNo) {

        return employeeRepository.existsByEmployeeNo(
                trimToNull(employeeNo));
    }

    /**
     * 従業員詳細画面に表示する基本情報を取得する。
     */
    public Employee getEmployee(Long employeeId) {

        Employee employee =
                employeeRepository.findById(employeeId);

        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "従業員が見つかりません。");
        }

        return employee;
    }

    /**
     * 担当営業として選択できるユーザーか確認する。
     */
    public boolean isActiveSalesUser(
            Long salesUserId) {

        if (salesUserId == null) {
            return false;
        }

        return userRepository
                .existsActiveSalesUserById(salesUserId);
    }

    /**
     * 従業員を新規登録する。
     */
    @Transactional
    public Long createEmployee(
            EmployeeCreateRequest request,
            Long loginUserId) {

        Employee employee = new Employee();

        employee.setEmployeeNo(
                trimToNull(request.getEmployeeNo()));
        employee.setEmployeeName(
                trimToNull(request.getEmployeeName()));
        employee.setBirthDate(request.getBirthDate());
        employee.setPhoneNo(
                trimToNull(request.getPhoneNo()));
        employee.setPostalCode(
                trimToNull(request.getPostalCode()));
        employee.setAddress(
                trimToNull(request.getAddress()));
        employee.setNearestStation(
                trimToNull(request.getNearestStation()));
        employee.setJoinDate(request.getJoinDate());
        employee.setEmploymentStatus(
                request.getEmploymentStatus());
        employee.setWorkStatus(
                request.getWorkStatus());
        employee.setSalesUserId(
                request.getSalesUserId());
        employee.setRemarks(
                trimToNull(request.getRemarks()));
        employee.setCreatedBy(loginUserId);
        employee.setUpdatedBy(loginUserId);

        employeeRepository.insert(employee);

        return employee.getEmployeeId();
    }

    /**
     * 従業員基本情報を更新する。
     */
    @Transactional
    public void updateEmployee(
            Long employeeId,
            EmployeeCreateRequest request,
            Long loginUserId) {

        Employee existingEmployee =
                getEmployee(employeeId);

        Employee employee = toEmployee(
                request,
                loginUserId);
        employee.setEmployeeId(employeeId);
        // 社員番号は登録後変更不可。Request値は使用しない。
        employee.setEmployeeNo(
                existingEmployee.getEmployeeNo());

        if (employeeRepository.update(employee) == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "従業員が見つかりません。");
        }
    }

    /**
     * 従業員と、その従業員に属するデータを論理削除する。
     */
    @Transactional
    public void deleteEmployee(
            Long employeeId,
            Long loginUserId,
            String roleCode) {

        if (!"ADMIN".equals(roleCode)) {
            throw new AccessDeniedException(
                    "従業員を削除できるのは管理者のみです。");
        }

        getEmployee(employeeId);

        // 外部キー関係の末端から順に論理削除する。
        employeeRepository.logicalDeleteInterviewQuestions(
                employeeId,
                loginUserId);
        employeeRepository.logicalDeleteInterviews(
                employeeId,
                loginUserId);
        employeeRepository.logicalDeleteMatchingResults(
                employeeId);
        employeeRepository.logicalDeleteAssignments(
                employeeId,
                loginUserId);
        employeeRepository.logicalDeleteEmployeeSkills(
                employeeId,
                loginUserId);

        if (employeeRepository.logicalDeleteEmployee(
                employeeId,
                loginUserId) == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "従業員が見つかりません。");
        }
    }

    /**
     * 登録・編集画面用Requestへ変換する。
     */
    public EmployeeCreateRequest toRequest(
            Employee employee) {

        EmployeeCreateRequest request =
                new EmployeeCreateRequest();
        request.setEmployeeNo(employee.getEmployeeNo());
        request.setEmployeeName(employee.getEmployeeName());
        request.setBirthDate(employee.getBirthDate());
        request.setPhoneNo(employee.getPhoneNo());
        request.setPostalCode(employee.getPostalCode());
        request.setAddress(employee.getAddress());
        request.setNearestStation(employee.getNearestStation());
        request.setJoinDate(employee.getJoinDate());
        request.setEmploymentStatus(
                employee.getEmploymentStatus());
        request.setWorkStatus(employee.getWorkStatus());
        request.setSalesUserId(employee.getSalesUserId());
        request.setRemarks(employee.getRemarks());
        return request;
    }

    private Employee toEmployee(
            EmployeeCreateRequest request,
            Long loginUserId) {

        Employee employee = new Employee();
        employee.setEmployeeNo(
                trimToNull(request.getEmployeeNo()));
        employee.setEmployeeName(
                trimToNull(request.getEmployeeName()));
        employee.setBirthDate(request.getBirthDate());
        employee.setPhoneNo(
                trimToNull(request.getPhoneNo()));
        employee.setPostalCode(
                trimToNull(request.getPostalCode()));
        employee.setAddress(
                trimToNull(request.getAddress()));
        employee.setNearestStation(
                trimToNull(request.getNearestStation()));
        employee.setJoinDate(request.getJoinDate());
        employee.setEmploymentStatus(
                request.getEmploymentStatus());
        employee.setWorkStatus(request.getWorkStatus());
        employee.setSalesUserId(request.getSalesUserId());
        employee.setRemarks(
                trimToNull(request.getRemarks()));
        employee.setCreatedBy(loginUserId);
        employee.setUpdatedBy(loginUserId);
        return employee;
    }

    /**
     * RequestをDomain層の検索条件へ変換する。
     */
    private EmployeeSearchCondition toSearchCondition(
            EmployeeSearchRequest request) {

        EmployeeSearchCondition condition =
                new EmployeeSearchCondition();

        condition.setEmployeeNo(
                trimToNull(request.getEmployeeNo()));

        condition.setEmployeeName(
                trimToNull(request.getEmployeeName()));

        condition.setWorkStatusCode(
                trimToNull(request.getWorkStatusCode()));

        condition.setSalesUserId(
                request.getSalesUserId());

        condition.setNearestStation(
                trimToNull(request.getNearestStation()));

        condition.setSkillId(
                request.getSkillId());

        condition.setMinExperienceMonths(
                request.getMinExperienceMonths());

        condition.setEndWithinDays(
                request.getEndWithinDays());

        return condition;
    }

    /**
     * Domain Modelを画面表示用Responseへ変換する。
     */
    private EmployeeSummaryResponse toEmployeeSummaryResponse(
            EmployeeSummary employee) {

        EmployeeSummaryResponse response =
                new EmployeeSummaryResponse();

        response.setEmployeeId(
                employee.getEmployeeId());

        response.setEmployeeNo(
                employee.getEmployeeNo());

        response.setEmployeeName(
                employee.getEmployeeName());

        response.setAge(
                employee.getAge());

        response.setNearestStation(
                employee.getNearestStation());

        response.setWorkStatusCode(
                employee.getWorkStatusCode());

        response.setWorkStatusName(
                employee.getWorkStatusName());

        response.setPrimarySkills(
                employee.getPrimarySkills());

        response.setCurrentProjectName(
                employee.getCurrentProjectName());

        response.setContractEndDate(
                employee.getContractEndDate());

        response.setRemainingDays(
                employee.getRemainingDays());

        response.setSalesUserName(
                employee.getSalesUserName());

        response.setWarningLevelCode(
                employee.getWarningLevelCode());

        return response;
    }

    /**
     * 総件数から総ページ数を計算する。
     */
    private int calculateTotalPages(
            long totalCount) {

        return (int) Math.ceil(
                (double) totalCount / PAGE_SIZE);
    }

    /**
     * ページ番号を利用可能な範囲に調整する。
     */
    private int normalizePage(
            Integer requestedPage,
            int totalPages) {

        if (requestedPage == null || requestedPage < 1) {
            return 1;
        }

        if (totalPages > 0
                && requestedPage > totalPages) {

            return totalPages;
        }

        return requestedPage;
    }

    /**
     * 前後の空白を除去し、空文字をnullに変換する。
     */
    private String trimToNull(
            String value) {

        if (value == null) {
            return null;
        }

        String trimmedValue = value.trim();

        if (trimmedValue.isEmpty()) {
            return null;
        }

        return trimmedValue;
    }
}
