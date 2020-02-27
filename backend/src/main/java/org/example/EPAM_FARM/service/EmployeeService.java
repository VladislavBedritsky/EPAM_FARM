package org.example.EPAM_FARM.service;

import org.example.EPAM_FARM.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Integer id);

    void saveEmployee(Employee employee, String departmentName);

    Employee returnNewEmployeeWithSetParameters (String name, String birthday, String salary);
}
