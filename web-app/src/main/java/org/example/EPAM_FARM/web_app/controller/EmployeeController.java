package org.example.EPAM_FARM.web_app.controller;


import org.example.EPAM_FARM.backend.model.Employee;
import org.example.EPAM_FARM.backend.service.DepartmentService;
import org.example.EPAM_FARM.backend.service.EmployeeService;
import org.example.EPAM_FARM.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Value("${project.web-app.name}")
    private String ARTIFACT_NAME;

    @GetMapping
    public String getEmployees(
            Model model) {

        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(model);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("artifact_name",ARTIFACT_NAME);

        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeView(
            Model model,
            @PathVariable Integer id
    ) {
        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(model);
        model.addAttribute("employee", employeeService.findById(id));
        model.addAttribute("artifact_name",ARTIFACT_NAME);

        return "employee";
    }

    @PostMapping
    public String saveEmployee(
            @RequestParam String name,
            @RequestParam String birthday,
            @RequestParam String salary,
            @RequestParam String departmentName,
            Model model
            ) {


        model.addAttribute("employees", employeeService.findAll());

        if (employeeService.isEmployeeNameAlreadyExists(name)) {
            model.addAttribute("isNameExists","Such employee is already exists!");
            return "employees";
        }else if(!employeeService.isProperFloatValue(salary)) {
            model.addAttribute("wrongFloatValue","Salary value is wrong (e.g. 1034.09)") ;
            return "employees";
        }


        Employee employee = employeeService.returnNewEmployeeWithSetParameters(
                name, birthday, salary
        );
        employeeService.saveEmployee(employee, departmentName);

        return "redirect:/employees";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {

        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @PostMapping("/update/{id}")
    public String updateEmployee (
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam String birthday,
            @RequestParam String salary,
            @RequestParam String departmentName,
            Model model
    ) {

        model.addAttribute("employees", employeeService.findAll());

        if (employeeService.isEmployeeNameAlreadyExists(name)) {
            model.addAttribute("isNameExists","Such employee is already exists!");
            return "employees";
        }else if(!employeeService.isProperFloatValue(salary)) {
            model.addAttribute("wrongFloatValue","Salary value is wrong (e.g. 1034.09)") ;
            return "employees";
        }

        Employee getEmployee = employeeService.returnNewEmployeeWithSetParameters(
                name,birthday,salary
        );
        employeeService.updateEmployee(id,getEmployee,departmentName);

        return "redirect:/employees";
    }

}
