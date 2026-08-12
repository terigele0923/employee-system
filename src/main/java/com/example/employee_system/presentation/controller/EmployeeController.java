package com.example.employee_system.presentation.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.employee_system.application.service.CodeService;
import com.example.employee_system.application.service.EmployeeAssignmentService;
import com.example.employee_system.application.service.EmployeeService;
import com.example.employee_system.application.service.EmployeeSkillService;
import com.example.employee_system.infrastructure.security.LoginUserDetails;
import com.example.employee_system.presentation.request.EmployeeCreateRequest;
import com.example.employee_system.presentation.request.EmployeeSkillRequest;
import com.example.employee_system.presentation.request.EmployeeUpdateRequest;
import com.example.employee_system.presentation.response.EmployeeAssignmentSummaryResponse;
import com.example.employee_system.presentation.response.EmployeeDetailResponse;
import com.example.employee_system.presentation.response.EmployeeSkillSummaryResponse;
import com.example.employee_system.presentation.response.EmployeeSummaryResponse;
import com.example.employee_system.presentation.response.SalesUserOptionResponse;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CodeService codeService;
    private final EmployeeSkillService employeeSkillService;
    private final EmployeeAssignmentService employeeAssignmentService;

    public EmployeeController(
            EmployeeService employeeService,
            CodeService codeService,
            EmployeeSkillService employeeSkillService,
            EmployeeAssignmentService employeeAssignmentService) {

        this.employeeService = employeeService;
        this.codeService = codeService;
        this.employeeSkillService = employeeSkillService;
        this.employeeAssignmentService = employeeAssignmentService;
    }
    
    @GetMapping(
    		"/employees/{employeeId}/skills/{employeeSkillId}/edit")
    public String editEmployeeSkill(
    		@PathVariable
			Long employeeId,
			@PathVariable
			Long employeeSkillId,
			@AuthenticationPrincipal
			LoginUserDetails loginUser,
			Model model) {
		
		Long salesUserId = resolveSalesUserId(loginUser);
		EmployeeDetailResponse employee = 
				employeeService.findEmployeeById(employeeId, salesUserId);
		
		if(employee == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND);
		}
		
		EmployeeSkillRequest employeeSkillRequest = 
				employeeSkillService.findEmployeeSkillById(
						employeeId, employeeSkillId);
		
		if(employeeSkillRequest == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND);
		}
		
		model.addAttribute("loginUser",loginUser.getUser());
		model.addAttribute("employee",employee);
		model.addAttribute("employeeSkillId",employeeSkillId);
		model.addAttribute("employeeSkillRequest",employeeSkillRequest);
		model.addAttribute("skillOptions",
				employeeSkillService.findActiveSkills());
		
		return "employee-skill-edit";
	}

    @GetMapping("/employees")
    public String employees(
    		@RequestParam(required = false)
    		Boolean contractEndingWithin30Days,
            @RequestParam(required = false)
            String employmentStatus,
            @RequestParam(required = false)
            String workStatus,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model) {

        Long salesUserId = null;

        if ("SALES".equals(
                loginUser.getUser().getRoleCode())) {

            salesUserId =
                    loginUser.getUser().getUserId();
        }

        List<EmployeeSummaryResponse> employees =
                employeeService.findEmployees(
                        employmentStatus,
                        workStatus,
                        contractEndingWithin30Days,
                        salesUserId);

        model.addAttribute(
                "loginUser",
                loginUser.getUser());

        model.addAttribute(
                "employees",
                employees);

        model.addAttribute(
                "employmentStatus",
                employmentStatus);

        model.addAttribute(
                "workStatus",
                workStatus);

        return "employees";
    }

    @GetMapping("/employees/{employeeId}")
    public String employeeDetail(
            @PathVariable
            Long employeeId,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model) {

        Long salesUserId = null;

        if ("SALES".equals(
                loginUser.getUser().getRoleCode())) {

            salesUserId =
                    loginUser.getUser().getUserId();
        }

        EmployeeDetailResponse employee =
                employeeService.findEmployeeById(
                        employeeId,
                        salesUserId);

        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        model.addAttribute(
                "loginUser",
                loginUser.getUser());

        model.addAttribute(
                "employee",
                employee);

        return "employee-detail";
    }
    
    @PostMapping("/employees/{employeeId}/skills/{employeeSkillId}/edit")
    public String updateEmployeeSkill(
			@PathVariable
			Long employeeId,
			@PathVariable
			Long employeeSkillId,
			@Valid
			@ModelAttribute
			EmployeeSkillRequest employeeSkillRequest,
			BindingResult bindingResult,
			@AuthenticationPrincipal
			LoginUserDetails loginUser,
			Model model) {
		
		Long salesUserId = resolveSalesUserId(loginUser);
		EmployeeDetailResponse employee = 
				employeeService.findEmployeeById(employeeId, salesUserId);
		
		if(employee == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND);
		}
		
		if(employeeSkillRequest.getSkillId() != null
				&& !employeeSkillService.existsActiveSkill(
						employeeSkillRequest.getSkillId())) {
			
			bindingResult.rejectValue(
					"skillId",
					"invalid",
					"有効なスキルを選択してください。");
		}
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("loginUser",loginUser.getUser());
			model.addAttribute("employee",employee);
			model.addAttribute("employeeSkillId",employeeSkillId);
			model.addAttribute("skillOptions",
					employeeSkillService.findActiveSkills());
			
			return "employee-skill-edit";
		}
		
		boolean updated =
		        employeeSkillService.updateEmployeeSkill(
		                employeeId,
		                employeeSkillId,
		                employeeSkillRequest,
		                loginUser.getUser().getUserId());

		if (!updated) {
		    throw new ResponseStatusException(
		            HttpStatus.NOT_FOUND);
		}
		
		return "redirect:/employees/" + employeeId + "/skills";
	}
    
    @GetMapping("/employees/{employeeId}/skills")
    public String employeeSkills(
    		@PathVariable
    		Long employeeId,
    		@AuthenticationPrincipal
    		LoginUserDetails loginUser,
    		Model model){
    	
    	Long salesUserId = resolveSalesUserId(loginUser);
    	EmployeeDetailResponse employee = 
    			employeeService.findEmployeeById(employeeId, salesUserId);
    	
    	if(employee == null){
    		throw new ResponseStatusException (
    				HttpStatus.NOT_FOUND);
    	}
    	
    	List<EmployeeSkillSummaryResponse> skills = 
    			employeeSkillService.findSkillsByEmployeeId(employeeId);
    	
    	model.addAttribute("loginUser",loginUser.getUser());
    	model.addAttribute("employee",employee);
    	model.addAttribute("skills",skills);
    	
    	return "employee-skills";
    }
    
    @GetMapping("/employees/{employeeId}/skills/new")
    public String newEmployeeSkill(
    		@PathVariable
    		Long employeeId,
    		@AuthenticationPrincipal
    		LoginUserDetails loginUser,
    		Model model) {
    	
    	Long salesUserId = resolveSalesUserId(loginUser);
    	EmployeeDetailResponse employee = 
    			employeeService.findEmployeeById(employeeId, salesUserId);
    	
    	if(employee == null) {
    		throw new ResponseStatusException(
    				HttpStatus.NOT_FOUND);
    	}
    	
    	model.addAttribute(
    			"loginUser",
    			loginUser.getUser());
    	
    	model.addAttribute("employee",
    			employee);
    	
    	model.addAttribute(
    			"employeeSkillRequest",
    			new EmployeeSkillRequest());
    	
    	model.addAttribute(
    			"skillOptions",
    			employeeSkillService.findActiveSkills());
    	
    	return "employee-skill-new";
    	
    }
    
    @PostMapping("/employees/{employeeId}/skills")
    public String createEmployeeSkill(
            @PathVariable
            Long employeeId,
            @Valid
            @ModelAttribute
            EmployeeSkillRequest employeeSkillRequest,
            BindingResult bindingResult,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model) {

        Long salesUserId =
                resolveSalesUserId(loginUser);

        EmployeeDetailResponse employee =
                employeeService.findEmployeeById(
                        employeeId,
                        salesUserId);

        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        Long skillId =
                employeeSkillRequest.getSkillId();

        if (skillId != null
                && !employeeSkillService.existsActiveSkill(
                        skillId)) {

            bindingResult.rejectValue(
                    "skillId",
                    "invalid",
                    "有効なスキルを選択してください。");
        }

        if (skillId != null
                && employeeSkillService.existsEmployeeSkill(
                        employeeId,
                        skillId)) {

            bindingResult.rejectValue(
                    "skillId",
                    "duplicate",
                    "このスキルはすでに登録されています。");
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "loginUser",
                    loginUser.getUser());

            model.addAttribute(
                    "employee",
                    employee);

            model.addAttribute(
                    "skillOptions",
                    employeeSkillService.findActiveSkills());

            return "employee-skill-new";
        }

        boolean created =
                employeeSkillService.createEmployeeSkill(
                        employeeId,
                        employeeSkillRequest,
                        loginUser.getUser().getUserId());

        if (!created) {
            throw new IllegalStateException(
                    "従業員スキルを登録できませんでした。");
        }

        return "redirect:/employees/"
                + employeeId
                + "/skills";
    }

    @GetMapping("/employees/{employeeId}/edit")
    public String editEmployee(
            @PathVariable
            Long employeeId,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model) {

        Long salesUserId =
                resolveSalesUserId(loginUser);

        EmployeeDetailResponse employee =
                employeeService.findEmployeeById(
                        employeeId,
                        salesUserId);

        if (employee == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        EmployeeUpdateRequest request =
                createUpdateRequest(employee);

        model.addAttribute(
                "loginUser",
                loginUser.getUser());

        model.addAttribute(
                "employee",
                employee);

        model.addAttribute(
                "employeeUpdateRequest",
                request);

        addCodeOptions(model);

        return "employee-edit";
    }

    @PostMapping("/employees/{employeeId}/edit")
    public String updateEmployee(
            @PathVariable
            Long employeeId,
            @ModelAttribute
            EmployeeUpdateRequest employeeUpdateRequest,
            @AuthenticationPrincipal
            LoginUserDetails loginUser) {

        Long salesUserId =
                resolveSalesUserId(loginUser);

        validateCodeOrThrow(
                CodeService.EMPLOYMENT_STATUS,
                employeeUpdateRequest.getEmploymentStatus());

        validateCodeOrThrow(
                CodeService.WORK_STATUS,
                employeeUpdateRequest.getWorkStatus());

        boolean updated =
                employeeService.updateEmployee(
                        employeeId,
                        employeeUpdateRequest,
                        salesUserId,
                        loginUser.getUser().getUserId());

        if (!updated) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        return "redirect:/employees/" + employeeId;
    }

    @PostMapping("/employees/{employeeId}/delete")
    public String deleteEmployee(
            @PathVariable
            Long employeeId,
            @AuthenticationPrincipal
            LoginUserDetails loginUser) {

        if (!"ADMIN".equals(
                loginUser.getUser().getRoleCode())) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN);
        }

        boolean deleted =
                employeeService.deleteEmployee(
                        employeeId,
                        loginUser.getUser().getUserId());

        if (!deleted) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        return "redirect:/employees";
    }
    
    @PostMapping("/employees/{employeeId}/skills/{employeeSkillId}/delete")
    public  String deleteEmployeeSkill(
    		@PathVariable
    		Long employeeId,
    		@PathVariable
    		Long employeeSkillId,
    		@AuthenticationPrincipal
    		LoginUserDetails loginUser) {
    	Long salesUserId = resolveSalesUserId(loginUser);
    	
    	EmployeeDetailResponse employee = 
				employeeService.findEmployeeById(employeeId, salesUserId);
    	
    	if(employee == null) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND);
		}
		boolean deleted = employeeSkillService.deleteEmployeeSkill(
				employeeId,
				employeeSkillId,
				loginUser.getUser().getUserId());
			if(!deleted) {
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND);
			}
			return "redirect:/employees/" + employeeId + "/skills";
    }

    @GetMapping("/employees/new")
    public String newEmployee(
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model) {

        model.addAttribute(
                "loginUser",
                loginUser.getUser());

        model.addAttribute(
                "employeeCreateRequest",
                new EmployeeCreateRequest());

        model.addAttribute(
                "salesUsers",
                resolveSelectableSalesUsers(
                        loginUser));

        addCodeOptions(model);

        return "employee-new";
    }

    @PostMapping("/employees")
    public String createEmployee(
            @Valid
            @ModelAttribute
            EmployeeCreateRequest employeeCreateRequest,
            BindingResult bindingResult,
            @AuthenticationPrincipal
            LoginUserDetails loginUser,
            Model model) {

        List<SalesUserOptionResponse> salesUsers =
                resolveSelectableSalesUsers(
                        loginUser);

        boolean validSalesUser =
                employeeCreateRequest.getSalesUserId() != null
                && salesUsers.stream().anyMatch(
                        salesUser -> salesUser.getUserId().equals(
                                employeeCreateRequest.getSalesUserId()));

        if (!validSalesUser) {
            bindingResult.rejectValue(
                    "salesUserId",
                    "invalid",
                    "営業担当者を選択してください。");
        }

        if (!codeService.exists(
                CodeService.EMPLOYMENT_STATUS,
                employeeCreateRequest.getEmploymentStatus())) {

            bindingResult.rejectValue(
                    "employmentStatus",
                    "invalid",
                    "在籍状態を選択してください。");
        }

        if (!codeService.exists(
                CodeService.WORK_STATUS,
                employeeCreateRequest.getWorkStatus())) {

            bindingResult.rejectValue(
                    "workStatus",
                    "invalid",
                    "稼働状態を選択してください。");
        }

        if (employeeCreateRequest.getEmployeeNo() != null
                && employeeService.existsByEmployeeNo(
                        employeeCreateRequest.getEmployeeNo())) {

            bindingResult.rejectValue(
                    "employeeNo",
                    "duplicate",
                    "この従業員番号はすでに使用されています。");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute(
                    "loginUser",
                    loginUser.getUser());

            model.addAttribute(
                    "salesUsers",
                    salesUsers);

            addCodeOptions(model);

            return "employee-new";
        }

        Long employeeId =
                employeeService.createEmployee(
                        employeeCreateRequest,
                        loginUser.getUser().getUserId());

        return "redirect:/employees/" + employeeId;
    }
    
    @GetMapping("employees/{employeeId}/assignments")
    public String employeeAssignments(
    		@PathVariable
    		Long employeeId,
    		@AuthenticationPrincipal
    		LoginUserDetails loginUser,
    		Model model) {
    	
    	Long salesUserId = resolveSalesUserId(loginUser);
    	
    	EmployeeDetailResponse employee = 
				employeeService.findEmployeeById(employeeId, salesUserId);
    	
    	if(employee == null) {
    		throw new ResponseStatusException(
					HttpStatus.NOT_FOUND);
    	}
    	
    	List<EmployeeAssignmentSummaryResponse> assignments = 
				employeeAssignmentService.findAssignmentsByEmployeeId(employeeId);
    	 model.addAttribute("loginUser",loginUser.getUser());
    	    model.addAttribute("employee",employee);
    		model.addAttribute("assignments",assignments);
    			
    			return "employee-assignments";
    }
    

    private Long resolveSalesUserId(
            LoginUserDetails loginUser) {

        if ("SALES".equals(
                loginUser.getUser().getRoleCode())) {

            return loginUser.getUser().getUserId();
        }

        return null;
    }

    private List<SalesUserOptionResponse>
            resolveSelectableSalesUsers(
                    LoginUserDetails loginUser) {

        List<SalesUserOptionResponse> salesUsers =
                employeeService.findSalesUsers();

        if (!"SALES".equals(
                loginUser.getUser().getRoleCode())) {

            return salesUsers;
        }

        return salesUsers.stream()
                .filter(salesUser ->
                        salesUser.getUserId().equals(
                                loginUser.getUser().getUserId()))
                .toList();
    }

    private void addCodeOptions(
            Model model) {

        model.addAttribute(
                "employmentStatuses",
                codeService.findByType(
                        CodeService.EMPLOYMENT_STATUS));

        model.addAttribute(
                "workStatuses",
                codeService.findByType(
                        CodeService.WORK_STATUS));
    }

    private void validateCodeOrThrow(
            String codeType,
            String codeValue) {

        if (!codeService.exists(
                codeType,
                codeValue)) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST);
        }
    }

    private EmployeeUpdateRequest createUpdateRequest(
            EmployeeDetailResponse employee) {

        EmployeeUpdateRequest request =
                new EmployeeUpdateRequest();

        request.setEmployeeName(
                employee.getEmployeeName());
        request.setBirthDate(
                employee.getBirthDate());
        request.setPhoneNo(
                employee.getPhoneNo());
        request.setPostalCode(
                employee.getPostalCode());
        request.setAddress(
                employee.getAddress());
        request.setNearestStation(
                employee.getNearestStation());
        request.setJoinDate(
                employee.getJoinDate());
        request.setEmploymentStatus(
                employee.getEmploymentStatus());
        request.setWorkStatus(
                employee.getWorkStatus());
        request.setRemarks(
                employee.getRemarks());

        return request;
    }
}
