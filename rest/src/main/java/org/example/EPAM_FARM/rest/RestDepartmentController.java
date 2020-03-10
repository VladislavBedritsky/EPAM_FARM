package org.example.EPAM_FARM.rest;

import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/departments", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public class RestDepartmentController {

    @Autowired
    private DepartmentService jdbcStorageService;

    @GetMapping
    public List<Department> getDepartments() {
        return jdbcStorageService.findAll();
    }

    @GetMapping("/{id}")
    public Department getDepartment(
            @PathVariable Integer id
    ) {
        return jdbcStorageService.findById(id);
    }

    @PostMapping
    public Department create(
            @RequestBody Department department
    ) {
        jdbcStorageService.saveDepartment(department);

        return jdbcStorageService.findAll().stream().reduce((first, second) -> second).orElse(null);
    }

    @PutMapping("/{id}")
    public Department update(
            @PathVariable Integer id,
            @RequestBody Department department
    ) {

        jdbcStorageService.updateDepartment(id, department.getName());

        return department;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ) {
        jdbcStorageService.deleteDepartment(id);
    }
}
