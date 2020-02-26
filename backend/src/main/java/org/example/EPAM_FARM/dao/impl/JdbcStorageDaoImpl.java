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

}
