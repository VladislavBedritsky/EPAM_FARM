package org.example.epam.backend.dao;

import org.example.epam.backend.model.Department;

import java.util.List;

/**
 * interface DepartmentDao
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public interface DepartmentDao {

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
     * Get average salary in department.
     *
     * @param id Department's id
     * @return average salary
     */
    Float getAverageSalaryInDepartment(Integer id);
}