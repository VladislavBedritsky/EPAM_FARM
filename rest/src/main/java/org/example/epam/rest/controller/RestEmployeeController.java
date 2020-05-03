package org.example.epam.rest.controller;

import org.example.epam.backend.model.Employee;
import org.example.epam.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * class RestEmployeeController
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@RestController
@RequestMapping(value = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Get all employees
     *
     * @return list of employees
     */
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    /**
     * Get employee with specified employee's id
     *
     * @param id Employee's id
     * @return employee
     */
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    /**
     * Get list of employees with specified department's id
     *
     * @param id Department's id
     * @return list of employees
     */
    @GetMapping("/byDepartmentId/{id}")
    public List<Employee> getEmployeesByDepartmentId(@PathVariable Integer id) {
        return employeeService.findEmployeesByDepartmentId(id);
    }

    /**
     * Create employee with authorities ADMIN or ROLE_ADMINS
     *
     * @param employee Employee
     * @return last employee in DB
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @PostMapping
    public Employee saveEmployee(
            @RequestBody Employee employee
    ) {

        employeeService.saveEmployee(employee, employee.getDepartment().getName());
        return employee;
    }

    /**
     * Update employee with authorities ADMIN or ROLE_ADMINS
     *
     * @param id Employee's id
     * @param employee Employee
     * @return employee
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Integer id,
            @RequestBody Employee employee
    ) {

        employeeService.updateEmployeeForRestController(employee,id);
        return employee;
    }

    /**
     * Delete employee with authorities ADMIN or ROLE_ADMINS
     *
     * @param id Employee's id
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','ROLE_ADMINS')")
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Integer id
    ) {
        employeeService.deleteEmployee(id);
    }
}
