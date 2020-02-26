package org.example.EPAM_FARM.dao.impl;

import org.example.EPAM_FARM.dao.DepartmentDao;
import org.example.EPAM_FARM.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource({"classpath:sql_department.properties", "classpath:db.properties"})
public class JdbcStorageDaoImpl implements DepartmentDao {

    private Connection connection;

    @Value("${db.username}")
    private String USERNAME;

    @Value("${db.password}")
    private String PASSWORD;

    @Value("${db.url}")
    private String URL;

    @Value("${department.findAll}")
    private String FIND_ALL;

    @Value("${employee.findById}")
    private String FIND_BY_ID;

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try {
            this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                departments.add(
                        new Department(
                                resultSet.getInt("id"),
                                resultSet.getString("name")));
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department findById(Integer id) {
        Department department = new Department();

        try {
            this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = this.connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
            }

                preparedStatement.close();
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return department;
    }

    @Override
    public void saveDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(Integer id) {

    }

}
