package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.DepartmentDao;
import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class JdbcStorageServiceImpl  implements DepartmentService {

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


}
