package org.example.epam.backend.service;

import org.example.epam.backend.model.Employee;

import java.util.List;

/**
 * interface EmployeeService
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public interface EmployeeService {

    /**
     * Get all employees
     *
     * @return list of employees
     */
    List<Employee> findAll();

    /**
     * Get employee with specified employee's id.
     *
     * @param id Employee's id
     * @return employee
     */
    Employee findById(Integer id);

    /**
     * Save employee with specified department's name.
     *
     * @param employee Employee
     * @param departmentName Department's name
     */
    void saveEmployee(Employee employee, String departmentName);

    /**
     * Get employee with new parameters: name, birthday, salary.
     *
     * @param name Employee's name
     * @param birthday Employee's birthday
     * @param salary Employee's salary
     * @return employee
     */
    Employee returnNewEmployeeWithSetParameters (String name, String birthday, String salary);

    /**
     * Delete employee.
     *
     * @param id Employee's id
     */
    void deleteEmployee(Integer id);

    /**
     * Check if employee's name is already exists.
     *
     * @param name Employee's name
     */
    boolean isEmployeeNameAlreadyExists(String name);

    /**
     * Check if salary is matching pattern.
     *
     * @param salary salary from UI from
     */
    boolean isProperFloatValue(String salary);

    /**
     * Update employee.
     *
     * @param employeeId Employee's id
     * @param employee Employee
     * @param departmentName Department's name
     */
    void updateEmployee(Integer employeeId, Employee employee, String departmentName);

    /**
     * Save employee for restController.
     *
     * @param employee Employee
     */
    void saveEmployeeForRestController(Employee employee);

    /**
     * Update employee for restController with specified employee's id.
     *
     * @param employee Employee
     * @param id Employee's id
     */
    void updateEmployeeForRestController(Employee employee, Integer id);

    /**
     * Get employee with specified employee's name.
     *
     * @param name Employees name
     * @return employee
     */
    Employee findEmployeeByName(String name);

    /**
     * Get list of employees with specified department's id.
     *
     * @param id Department's id
     * @return list of employees
     */
    List<Employee> findEmployeesByDepartmentId(Integer id);
}
