package org.example.epam.backend.dao;

import org.example.epam.backend.model.Employee;

import java.util.List;

/**
 * interface EmployeeDao
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public interface EmployeeDao {


    /**
     * Get all employees
     *
     * @return list of employees
     */
    List<Employee> findAll ();

    /**
     * Get employee with specified employee's id.
     *
     * @param id Employee's id
     * @return employee
     */
    Employee findById(Integer id);

    /**
     * Get list of employees with specified department's id.
     *
     * @param id Department's id
     * @return list of employees
     */
    List<Employee> findEmployeesByDepartmentId(Integer id);

    /**
     * Save employee with specified department's id.
     *
     * @param employee Employee
     * @param departmentId Department's id
     */
    void saveEmployee(Employee employee, Integer departmentId);

    /**
     * Delete employee.
     *
     * @param id Employee's id
     */
    void deleteEmployee(Integer id);

    /**
     * Update employee.
     *
     * @param employeeId Employee's id
     * @param employee Employee
     * @param departmentId Department's id
     */
    void updateEmployee(Integer employeeId, Employee employee, Integer departmentId);

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
     * @param name Employee's name
     * @return employee
     */
    Employee findEmployeeByName(String name);
}
