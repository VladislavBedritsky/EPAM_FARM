package org.example.epamFarm.rest.controller;

import org.example.epamFarm.backend.model.Employee;
import org.example.epamFarm.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
//    @JsonView(View.FullEmployeesWithoutDepartment.class)
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
//    @JsonView(View.FullEmployeesWithoutDepartment.class)
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    @GetMapping("/byDepartmentId/{id}")
    public List<Employee> getEmployeesByDepartmentId(@PathVariable Integer id) {
        return employeeService.findEmployeesByDepartmentId(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @PostMapping
    public Employee saveEmployee(
            @RequestBody Employee employee
    ) {

        employeeService.saveEmployee(employee, employee.getDepartment().getName());
        return employeeService.findAll().stream().reduce((first, second) -> second).orElse(null);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Integer id,
            @RequestBody Employee employee
    ) {

        employeeService.updateEmployeeForRestController(employee,id);
        return employee;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ) {
        employeeService.deleteEmployee(id);
    }
}
