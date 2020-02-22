package org.example.EPAM_FARM.controller;


import org.example.EPAM_FARM.service.DepartmentService;
import org.example.EPAM_FARM.service.impl.DepartmentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;

@Controller
public class MainController {

    private DepartmentService departmentService;

    public MainController(DataSource dataSource) {
        departmentService = new DepartmentServiceImpl(dataSource);
    }

    @GetMapping("/")
    public String getIndex (Model model) {
        model.addAttribute("q",departmentService.findAll());
        return "index";
    }


}