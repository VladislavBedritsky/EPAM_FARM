package org.example.EPAM_FARM.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.EPAM_FARM.json_view.View;
import org.example.EPAM_FARM.model.Employee;
import org.example.EPAM_FARM.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @JsonView(View.FullEmployeesWithoutDepartment.class)
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }
}
