package org.example.EPAM_FARM.backend.service;

import org.example.EPAM_FARM.backend.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Integer id);

    void saveEmployee(Employee employee, String departmentName);

    Employee returnNewEmployeeWithSetParameters (String name, String birthday, String salary);

    void deleteEmployee(Integer id);

    boolean isEmployeeNameAlreadyExists(String name);

    boolean isProperFloatValue(String salary);

    void updateEmployee(Integer employeeId, Employee employee, String departmentName);

    void saveEmployeeForRestController(Employee employee);

    void updateEmployeeForRestController(Employee employee, Integer id);

    Employee findEmployeeByName(String name);
}
