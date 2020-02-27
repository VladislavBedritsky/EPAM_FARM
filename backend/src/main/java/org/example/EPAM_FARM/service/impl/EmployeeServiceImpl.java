package org.example.EPAM_FARM.service.impl;

import org.example.EPAM_FARM.dao.EmployeeDao;
import org.example.EPAM_FARM.model.Employee;
import org.example.EPAM_FARM.service.ConverterService;
import org.example.EPAM_FARM.service.DepartmentService;
import org.example.EPAM_FARM.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeDao.findById(id);
    }

    @Override
    public void saveEmployee(Employee employee, String departmentName) {
        employeeDao.saveEmployee(
                employee,
                departmentService.findDepartmentIdByDepartmentName(departmentName));
    }

    @Override
    public Employee returnNewEmployeeWithSetParameters(String name, String birthday, String salary) {
        Employee employee = new Employee();

        employee.setName(name);
        employee.setBirthday(
                converterService.convertStringToLocalDate(birthday));
        employee.setSalary(
                converterService.convertFromStringToFloat(salary));

        return employee;
    }

    @Override
    public void deleteDepartment(Integer id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public boolean isEmployeeNameAlreadyExists(String name) {

        List<Employee> employees = findAll();
        for (Employee temp:employees) {
            if(name.equalsIgnoreCase(temp.getName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isProperFloatValue(String salary) {

        String regex = "^\\d*\\.?\\d*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(salary);

        return matcher.find();
    }

    @Override
    public void updateEmployee(Integer employeeId, Employee employee, String departmentName) {
        employeeDao.updateEmployee(
                employeeId,
                employee,
                departmentService.findDepartmentIdByDepartmentName(departmentName)
        );
    }

    @Override
    public void saveEmployeeForRestController(Employee employee) {
        employeeDao.saveEmployeeForRestController(employee);
    }

    @Override
    public void updateEmployeeForRestController(Employee employee, Integer id) {
        employeeDao.updateEmployeeForRestController(employee,id);
    }

}
