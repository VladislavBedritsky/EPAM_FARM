package org.example.EPAM_FARM.backend.service;

import org.example.EPAM_FARM.backend.model.Department;
import org.example.EPAM_FARM.backend.model.Employee;

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

    Integer findDepartmentIdByDepartmentName(String name);

    Float getAverageSalaryInDepartment(Integer id);
}