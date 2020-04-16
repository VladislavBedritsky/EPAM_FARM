package org.example.epam.backend.service;

import org.example.epam.backend.model.Department;
import org.example.epam.backend.model.Employee;

import java.util.List;

/**
 * interface DepartmentService
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public interface DepartmentService {

    /**
     * Get all departments.
     *
     * @return list of departments
     */
    List<Department> findAll();

    /**
     * Get department with specified department's id.
     *
     * @param id Department's id
     * @return department
     */
    Department findById(Integer id);

    /**
     * Save department.
     *
     * @param department Department
     */
    void saveDepartment(Department department);

    /**
     * Delete department.
     *
     * @param id Department's id
     */
    void deleteDepartment(Integer id);

    /**
     * Update department.
     *
     * @param id Department's id
     * @param name Department's name
     */
    void updateDepartment(Integer id, String name);

    /**
     * Get employees with specified department's id.
     *
     * @param id Department's id
     * @return list of employees
     */
    List<Employee> findEmployeesByDepartmentId(Integer id);

    /**
     * Check if department's name is already exists in DB.
     *
     * @param name Department's name
     * @return boolean
     */
    boolean isDepartmentNameAlreadyExists(String name);

    /**
     * Get department with new name.
     *
     * @param name Department's name
     * @return department
     */
    Department returnNewDepartmentWithName(String name);

    /**
     * Get department's id with specified department's name.
     *
     * @param name Department's name
     * @return department's id
     */
    Integer findDepartmentIdByDepartmentName(String name);

    /**
     * Get average salary in department.
     *
     * @param id Department's id
     * @return average salary
     */
    Float getAverageSalaryInDepartment(Integer id);
}
