package org.example.epam.backend.dao;

import org.example.epam.backend.model.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> findAll();

    Department findById(Integer id);

    void saveDepartment(Department department);

    void deleteDepartment(Integer id);

    void updateDepartment(Integer id, String name);

    Float getAverageSalaryInDepartment(Integer id);
}