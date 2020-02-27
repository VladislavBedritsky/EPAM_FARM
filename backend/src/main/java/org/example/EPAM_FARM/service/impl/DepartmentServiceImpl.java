package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.DepartmentDao;
import org.example.EPAM_FARM.dao.EmployeeDao;
import org.example.EPAM_FARM.model.Department;
import org.example.EPAM_FARM.model.Employee;
import org.example.EPAM_FARM.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentDao.findById(id);
    }

    @Override
    public void saveDepartment(Department department) {
       departmentDao.saveDepartment(department);
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentDao.deleteDepartment(id);
    }

    @Override
    public void updateDepartment(Integer id, String name) {
        departmentDao.updateDepartment(id, name);
    }

    @Override
    public List<Employee> findEmployeesByDepartmentId(Integer id) {
        return employeeDao.findEmployeesByDepartmentId(id);
    }

    @Override
    public boolean isDepartmentNameAlreadyExists(String name) {

        List<Department> departments = findAll();

        for (Department temp:departments) {
            if(name.equalsIgnoreCase(temp.getName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Department returnNewDepartmentWithName(String name) {
        Department department = new Department();
        department.setName(name);
        return department;
    }


}