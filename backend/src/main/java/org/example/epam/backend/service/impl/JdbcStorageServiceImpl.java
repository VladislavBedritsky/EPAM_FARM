package org.example.epam.backend.service.impl;

import org.example.epam.backend.dao.DepartmentDao;
import org.example.epam.backend.model.Department;
import org.example.epam.backend.model.Employee;
import org.example.epam.backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdbcStorageServiceImpl  implements DepartmentService {

    @Autowired
    private DepartmentDao jdbcStorageDao;

    @Override
    public List<Department> findAll() {
        return jdbcStorageDao.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return jdbcStorageDao.findById(id);
    }

    @Override
    public void saveDepartment(Department department) {
        jdbcStorageDao.saveDepartment(department);
    }

    @Override
    public void deleteDepartment(Integer id) {
        jdbcStorageDao.deleteDepartment(id);
    }

    @Override
    public void updateDepartment(Integer id, String name) {
        jdbcStorageDao.updateDepartment(id,name);
    }

    @Override
    public List<Employee> findEmployeesByDepartmentId(Integer id) {
        return null;
    }

    @Override
    public boolean isDepartmentNameAlreadyExists(String name) {
        return false;
    }

    @Override
    public Department returnNewDepartmentWithName(String name) {
        return null;
    }

    @Override
    public Integer findDepartmentIdByDepartmentName(String name) {
        return null;
    }

    @Override
    public Float getAverageSalaryInDepartment(Integer id) {
        return null;
    }

}
