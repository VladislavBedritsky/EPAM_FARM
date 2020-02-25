package org.example.EPAM_FARM.rest;

import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class RestDepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.findAll();
    }

}
