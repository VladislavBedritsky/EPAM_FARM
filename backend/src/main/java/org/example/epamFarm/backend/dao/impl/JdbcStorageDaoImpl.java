package org.example.epamFarm.backend.dao.impl;

import org.example.epamFarm.backend.dao.DepartmentDao;
import org.example.epamFarm.backend.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource({"classpath:sql_department.properties"})
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

    @Value("${department.findById}")
    private String FIND_BY_ID;

    @Value("${department.saveDepartment}")
    private String SAVE_DEPARTMENT;

    @Value("${department.deleteDepartment}")
    private String DELETE_DEPARTMENT;

    @Value("${department.updateDepartment}")
    private String UPDATE_DEPARTMENT;

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            preparedStatement = this.connection.prepareStatement(FIND_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                departments.add(
                        new Department(
                                resultSet.getInt("id"),
                                resultSet.getString("name")));
            }

            resultSet.close();
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

        try {
            this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = this.connection.prepareStatement(SAVE_DEPARTMENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteDepartment(Integer id) {
        try {
            this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_DEPARTMENT);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDepartment(Integer id, String name) {
        try {
            this.connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_DEPARTMENT);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Float getAverageSalaryInDepartment(Integer id) {
        return null;
    }


}
