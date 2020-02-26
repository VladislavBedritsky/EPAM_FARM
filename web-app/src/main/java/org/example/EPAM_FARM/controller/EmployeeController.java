package org.example.EPAM_FARM.controller;

import org.example.EPAM_FARM.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getEmployees(Model model) {
        model.addAttribute("employees",employeeService.findAll());
        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeView(
            Model model,
            @PathVariable Integer id
    ) {
        model.addAttribute("employee",employeeService.findById(id));
        return "employee";
    }
}
