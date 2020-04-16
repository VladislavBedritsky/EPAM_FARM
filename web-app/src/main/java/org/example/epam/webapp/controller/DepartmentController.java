package org.example.epam.webapp.controller;

import org.example.epam.backend.model.Department;
import org.example.epam.backend.service.DepartmentService;
import org.example.epam.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * class DepartmentController
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService jdbcStorageService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;

    /**
     * Get all departments
     *
     * @param model Model
     * @return departments.html
     */
    @GetMapping
    public String getDepartments (
            Model model) {

        userService.checkIfUserAuthenticatedAndHasRoleAdminInLdapAndDatabaseWhenAddToModel(model);
        model.addAttribute("departments", jdbcStorageService.findAll());

        return "departments";
    }

    /**
     * Get department with specified department's id
     *
     * @param model Model
     * @param id Department's id
     * @return department.html
     */
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

    /**
     * Create department with authorities ADMIN or ROLE_ADMINS
     *
     * @param name Department's name
     * @param model Model
     * @return departments.html
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
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

    /**
     * Delete department with authorities ADMIN or ROLE_ADMINS
     *
     * @param id Department's id
     * @return departments.html
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @GetMapping("/delete/{id}")
    public String deleteDepartment (
            @PathVariable Integer id
    ) {

        jdbcStorageService.deleteDepartment(id);

        return "redirect:/departments";
    }


    /**
     * Update department with authorities ADMIN or ROLE_ADMINS
     *
     * @param id Department's id
     * @param name Department's name
     * @return departments.html
     */
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