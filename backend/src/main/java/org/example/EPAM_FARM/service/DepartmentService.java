package org.example.EPAM_FARM.service;

import org.example.EPAM_FARM.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> findAll();

    Department findById(Integer id);

    void saveDepartment(Department department);

    void deleteDepartment(Integer id);
}
