package org.example.EPAM_FARM.controller;


import org.example.EPAM_FARM.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/")
    public String getQ (Model model) {
        model.addAttribute("q",departmentService.findAll());
        return "index";
    }

}
