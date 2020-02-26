package org.example.EPAM_FARM.dao;

import org.example.EPAM_FARM.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll ();

    Employee findById(Integer id);
}
