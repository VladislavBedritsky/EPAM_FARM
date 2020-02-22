package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.DepartmentDao;
import org.example.EPAM_FARM.dao.impl.DepartmentDaoImpl;
import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DataSource dataSource) {
        departmentDao = new DepartmentDaoImpl(dataSource);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
