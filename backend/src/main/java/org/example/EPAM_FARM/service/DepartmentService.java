package org.example.EPAM_FARM.service;

import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.model.Employee;

import java.util.List;

public interface DepartmentService {

    List<Department> findAll();

    Department findById(Integer id);

    void saveDepartment(Department department);

    void deleteDepartment(Integer id);

    void updateDepartment(Integer id, String name);

    List<Employee> findEmployeesByDepartmentId(Integer id);

    boolean isDepartmentNameAlreadyExists(String name);

    Department returnNewDepartmentWithName(String name);
}
