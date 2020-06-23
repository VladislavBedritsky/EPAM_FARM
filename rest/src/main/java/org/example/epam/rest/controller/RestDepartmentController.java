package org.example.epam.rest.controller;

import org.example.epam.backend.model.Department;
import org.example.epam.backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST Controller that handle requests about departments
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@RestController
@RequestMapping(path = "/departments",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestDepartmentController {

    @Autowired
    private DepartmentService jdbcStorageService;

    /**
     * Get all departments
     *
     * @return list of departments
     */
    @GetMapping
    public List<Department> getDepartments() {
        return jdbcStorageService.findAll();
    }

    /**
     * Get department with specified department's id
     *
     * @param id Department's id
     * @return department
     */
    @GetMapping("/{id}")
    public Department getDepartment(
            @PathVariable Integer id
    ) {
        return jdbcStorageService.findById(id);
    }

    /**
     * Create department with authorities ADMIN or ROLE_ADMINS
     *
     * @param department Department
     * @return last department in DB
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @PostMapping
    public Department create(
            @RequestBody @Valid Department department
    ) {
        jdbcStorageService.saveDepartment(department);

        return jdbcStorageService.findAll().stream().reduce((first, second) -> second).orElse(null);
    }

    /**
     * Update department with authorities ADMIN or ROLE_ADMINS
     *
     * @param id Department's id
     * @param department Department
     * @return department
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @PutMapping("/{id}")
    public Department update(
            @PathVariable Integer id,
            @RequestBody @Valid Department department
    ) {

        jdbcStorageService.updateDepartment(id, department.getName());

        return department;
    }

    /**
     * Delete department with authorities ADMIN or ROLE_ADMINS
     *
     * @param id Department's id
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ) {
        jdbcStorageService.deleteDepartment(id);
    }
}
