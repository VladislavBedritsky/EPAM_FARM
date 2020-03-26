package org.example.EPAM_FARM.web_app.controller;

import org.example.EPAM_FARM.backend.model.Department;
import org.example.EPAM_FARM.backend.service.DepartmentService;
import org.example.EPAM_FARM.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService jdbcStorageService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getDepartments (
            Model model) {

        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(model);
        model.addAttribute("departments", jdbcStorageService.findAll());

        return "departments";
    }

    @GetMapping("/{id}")
    public String getDepartmentView (
            Model model,
            @PathVariable Integer id) {

        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(model);
        model.addAttribute("department", jdbcStorageService.findById(id));
        model.addAttribute("department_employees", departmentService.findEmployeesByDepartmentId(id));
        model.addAttribute("average_salary",departmentService.getAverageSalaryInDepartment(id));

        return "department";
    }

    @PostMapping
    public String saveDepartment (
            @RequestParam String name,
            Model model
    ) {

        model.addAttribute("departments", jdbcStorageService.findAll());
        if (departmentService.isDepartmentNameAlreadyExists(name)) {
            model.addAttribute("isExists","Such department is already exists!");
            return "departments";
        }

        Department department = departmentService.returnNewDepartmentWithName(name);
        jdbcStorageService.saveDepartment(department);

        return "redirect:/departments";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @GetMapping("/delete/{id}")
    public String deleteDepartment (
            @PathVariable Integer id
    ) {

        jdbcStorageService.deleteDepartment(id);

        return "redirect:/departments";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @PostMapping("/update/{id}")
    public String updateDepartment (
            @PathVariable Integer id,
            @RequestParam String name
    ) {


        jdbcStorageService.updateDepartment(id, name);

        return "redirect:/departments";
    }

}