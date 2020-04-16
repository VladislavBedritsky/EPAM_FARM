package org.example.epam.backend.service.impl;

import org.example.epam.backend.dao.DepartmentDao;
import org.example.epam.backend.dao.EmployeeDao;
import org.example.epam.backend.model.Department;
import org.example.epam.backend.model.Employee;
import org.example.epam.backend.service.DepartmentService;
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

    @Override
    public Integer findDepartmentIdByDepartmentName(String name) {
        List<Department> departments = findAll();
        Integer departmentId = 1;

        for (Department temp:departments) {
            if(temp.getName().equalsIgnoreCase(name)) {
                departmentId = temp.getId();
            }
        }

        return departmentId;
    }

    @Override
    public Float getAverageSalaryInDepartment(Integer id) {
        return departmentDao.getAverageSalaryInDepartment(id);
    }

}