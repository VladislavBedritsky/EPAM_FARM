package org.example.EPAM_FARM.dao;

import org.example.EPAM_FARM.model.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> findAll();

    Department findById(Integer id);

    void saveDepartment(Department department);

    void deleteDepartment(Integer id);
}