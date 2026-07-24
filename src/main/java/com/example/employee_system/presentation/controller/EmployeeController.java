package com.example.employee_system.presentation.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.employee_system.application.service.EmployeeService;
import com.example.employee_system.infrastructure.security.LoginUserDetails;
import com.example.employee_system.presentation.request.EmployeeCreateRequest;
import com.example.employee_system.presentation.request.EmployeeSearchRequest;
import com.example.employee_system.presentation.response.EmployeeSearchResponse;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(
            EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    /**
     * 従業員一覧画面を表示する。
     */
    @GetMapping("/employees")
    public String employees(
            @ModelAttribute("searchRequest")
            EmployeeSearchRequest searchRequest,
            Model model) {

        EmployeeSearchResponse searchResult =
                employeeService.searchEmployees(
                        searchRequest);

        model.addAttribute(
                "searchResult",
                searchResult);

        return "employees";
    }

    /**
     * 従業員新規登録画面を表示する。
     */
    @GetMapping("/employees/new")
    public String newEmployee(
            Model model) {

        if (!model.containsAttribute(
                "employeeCreateRequest")) {

            model.addAttribute(
                    "employeeCreateRequest",
                    new EmployeeCreateRequest());
        }

        addFormOptions(model);

        return "employee-form";
    }

    /**
     * 従業員詳細画面を表示する。
     */
    @GetMapping("/employees/{employeeId}")
    public String employeeDetail(
            @PathVariable Long employeeId,
            Model model) {

        model.addAttribute(
                "employee",
                employeeService.getEmployee(employeeId));

        return "employee-detail";
    }

    /**
     * 従業員編集画面を表示する。
     */
    @GetMapping("/employees/{employeeId}/edit")
    public String editEmployee(
            @PathVariable Long employeeId,
            Model model) {

        if (!model.containsAttribute(
                "employeeCreateRequest")) {

            model.addAttribute(
                    "employeeCreateRequest",
                    employeeService.toRequest(
                            employeeService.getEmployee(
                                    employeeId)));
        }

        model.addAttribute("employeeId", employeeId);
        model.addAttribute("editMode", true);
        addFormOptions(model);
        return "employee-form";
    }

    /**
     * 従業員を新規登録する。
     */
    @PostMapping("/employees")
    public String createEmployee(
            @Valid
            @ModelAttribute("employeeCreateRequest")
            EmployeeCreateRequest request,
            BindingResult bindingResult,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model,
            RedirectAttributes redirectAttributes) {

        validateCreateRequest(
                request,
                bindingResult);

        if (bindingResult.hasErrors()) {
            addFormOptions(model);
            return "employee-form";
        }

        try {
            employeeService.createEmployee(
                    request,
                    loginUser.getUser().getUserId());

        } catch (DuplicateKeyException exception) {

            bindingResult.rejectValue(
                    "employeeNo",
                    "duplicate",
                    "この社員番号は既に登録されています。");

            addFormOptions(model);
            return "employee-form";
        }

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "従業員を登録しました。");

        return "redirect:/employees";
    }

    /**
     * 従業員基本情報を更新する。
     */
    @PostMapping("/employees/{employeeId}")
    public String updateEmployee(
            @PathVariable Long employeeId,
            @Valid
            @ModelAttribute("employeeCreateRequest")
            EmployeeCreateRequest request,
            BindingResult bindingResult,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model,
            RedirectAttributes redirectAttributes) {

        validateUpdateRequest(
                employeeId,
                request,
                bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("employeeId", employeeId);
            model.addAttribute("editMode", true);
            addFormOptions(model);
            return "employee-form";
        }

        employeeService.updateEmployee(
                employeeId,
                request,
                loginUser.getUser().getUserId());

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "従業員情報を更新しました。");

        return "redirect:/employees/" + employeeId;
    }

    /**
     * 従業員と関連データを論理削除する。
     */
    @PostMapping("/employees/{employeeId}/delete")
    public String deleteEmployee(
            @PathVariable Long employeeId,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            RedirectAttributes redirectAttributes) {

        employeeService.deleteEmployee(
                employeeId,
                loginUser.getUser().getUserId(),
                loginUser.getUser().getRoleCode());

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "従業員と関連データを削除しました。");

        return "redirect:/employees";
    }

    private void validateCreateRequest(
            EmployeeCreateRequest request,
            BindingResult bindingResult) {

        if (!bindingResult.hasFieldErrors(
                "employeeNo")
                && employeeService.existsByEmployeeNo(
                        request.getEmployeeNo())) {

            bindingResult.rejectValue(
                    "employeeNo",
                    "duplicate",
                    "この社員番号は既に登録されています。");
        }

        if (!bindingResult.hasFieldErrors(
                "salesUserId")
                && !employeeService.isActiveSalesUser(
                        request.getSalesUserId())) {

            bindingResult.rejectValue(
                    "salesUserId",
                    "invalid",
                    "有効な担当営業を選択してください。");
        }

        if (request.getBirthDate() != null
                && request.getJoinDate() != null
                && !request.getJoinDate().isAfter(
                        request.getBirthDate())) {

            bindingResult.rejectValue(
                    "joinDate",
                    "invalidPeriod",
                    "入社日は生年月日より後の日付を入力してください。");
        }
    }

    private void validateUpdateRequest(
            Long employeeId,
            EmployeeCreateRequest request,
            BindingResult bindingResult) {

        if (!bindingResult.hasFieldErrors("salesUserId")
                && !employeeService.isActiveSalesUser(
                        request.getSalesUserId())) {

            bindingResult.rejectValue(
                    "salesUserId",
                    "invalid",
                    "有効な担当営業を選択してください。");
        }

        if (request.getBirthDate() != null
                && request.getJoinDate() != null
                && !request.getJoinDate().isAfter(
                        request.getBirthDate())) {

            bindingResult.rejectValue(
                    "joinDate",
                    "invalidPeriod",
                    "入社日は生年月日より後の日付を入力してください。");
        }
    }

    private void addFormOptions(
            Model model) {

        model.addAttribute(
                "salesUsers",
                employeeService.getActiveSalesUsers());
    }
}
