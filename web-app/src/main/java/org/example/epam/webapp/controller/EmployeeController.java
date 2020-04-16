package org.example.epam.webapp.controller;


import org.example.epam.backend.model.Employee;
import org.example.epam.backend.service.DepartmentService;
import org.example.epam.backend.service.EmployeeService;
import org.example.epam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * class EmployeeController
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;

    /**
     * Get all employees
     *
     * @param model Model
     * @return employees.html
     */
    @GetMapping
    public String getEmployees(
            Model model) {

        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(model);
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("departments", departmentService.findAll());

        return "employees";
    }

    /**
     * Get employee with specified employee's id
     *
     * @param model Model
     * @param id Employee's id
     * @return employee.html
     */
    @GetMapping("/{id}")
    public String getEmployeeView(
            Model model,
            @PathVariable Integer id
    ) {
        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(model);
        model.addAttribute("employee", employeeService.findById(id));
        return "employee";
    }

    /**
     * Create employee with authorities ADMIN or ROLE_ADMINS
     *
     * @param name Employee's name
     * @param birthday Employee's birthday
     * @param salary Employee's salary
     * @param departmentName Department's name
     * @return employees.html
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
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

    /**
     * Delete employee with authorities ADMIN or ROLE_ADMINS
     *
     * @param id Employee's id
     * @return employees.html
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {

        employeeService.deleteEmployee(id);

        return "redirect:/employees";
    }

    /**
     * Update employee with authorities ADMIN or ROLE_ADMINS
     *
     * @param id Employee's id
     * @param name Employee's name
     * @param birthday Employee's birthday
     * @param salary Employee's birthday
     * @param departmentName Department's name
     * @return employees.html
     */
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
