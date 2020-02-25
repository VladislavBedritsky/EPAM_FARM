package org.example.EPAM_FARM.controller;


import org.example.EPAM_FARM.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService jdbcStorageService;

    @GetMapping("/")
    public String getDepartments (Model model) {
        model.addAttribute("q", jdbcStorageService.findAll());
        return "departments";
    }


}