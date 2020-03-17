package org.example.EPAM_FARM.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.EPAM_FARM.backend.json_view.View;
import org.example.EPAM_FARM.backend.model.Employee;
import org.example.EPAM_FARM.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @JsonView(View.FullEmployeesWithoutDepartment.class)
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(View.FullEmployeesWithoutDepartment.class)
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public Employee create(
            @RequestBody Employee employee
    ) {

        employeeService.saveEmployeeForRestController(employee);
        return employeeService.findAll().stream().reduce((first, second) -> second).orElse(null);
    }

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Integer id,
            @RequestBody Employee employee
    ) {

        employeeService.updateEmployeeForRestController(employee,id);
        return employee;
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ) {
        employeeService.deleteEmployee(id);
    }
}
