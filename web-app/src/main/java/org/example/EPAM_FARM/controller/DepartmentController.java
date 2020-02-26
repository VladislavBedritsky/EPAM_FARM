package org.example.EPAM_FARM.controller;


import org.example.EPAM_FARM.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService jdbcStorageService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getDepartments (Model model) {
        model.addAttribute("departments", jdbcStorageService.findAll());
        return "departments";
    }

    @GetMapping("/{id}")
    public String getDepartmentView (
            Model model,
            @PathVariable Integer id) {

        model.addAttribute("department", jdbcStorageService.findById(id));

        return "department";
    }


}