package org.example.epam.backend.dao;

import org.example.epam.backend.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll ();

    Employee findById(Integer id);

    List<Employee> findEmployeesByDepartmentId(Integer id);

    void saveEmployee(Employee employee, Integer departmentId);

    void deleteEmployee(Integer id);

    void updateEmployee(Integer employeeId, Employee employee, Integer departmentId);

    void saveEmployeeForRestController(Employee employee);

    void updateEmployeeForRestController(Employee employee, Integer id);

    Employee findEmployeeByName(String name);

}
