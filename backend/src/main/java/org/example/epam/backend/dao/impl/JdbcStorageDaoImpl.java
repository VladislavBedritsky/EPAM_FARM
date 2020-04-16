package org.example.epam.backend.dao.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.epam.backend.dao.DepartmentDao;
import org.example.epam.backend.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DepartmentDao interface implementation
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
@Repository
@PropertySource({"classpath:sql_department.properties"})
public class JdbcStorageDaoImpl implements DepartmentDao {

    private static Logger LOGGER = LogManager.getLogger(JdbcStorageDaoImpl.class);
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.url}")
    private String url;
    @Value("${department.findAll}")
    private String findAll;
    @Value("${department.findById}")
    private String findById;
    @Value("${department.saveDepartment}")
    private String saveDepartment;
    @Value("${department.deleteDepartment}")
    private String deleteDepartment;
    @Value("${department.updateDepartment}")
    private String updateDepartment;

    @Override
    @SuppressFBWarnings("OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE")
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();

        try ( Connection connection = DriverManager.getConnection(url, username, password);
              PreparedStatement preparedStatement = connection.prepareStatement(findAll);
              ResultSet resultSet = preparedStatement.executeQuery();
        ) {

            while (resultSet.next()) {
                departments.add(
                        new Department(
                                resultSet.getInt("id"),
                                resultSet.getString("name")));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return departments;
    }

    @Override
    @SuppressFBWarnings("OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE")
    public Department findById(Integer id) {
        Department department = new Department();

        try ( Connection connection = DriverManager.getConnection(url, username, password);
              PreparedStatement preparedStatement = connection.prepareStatement(findById);
              ResultSet resultSet = preparedStatement.executeQuery();
              ) {

            preparedStatement.setInt(1,id);

            while (resultSet.next()) {
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return department;
    }

    @Override
    @SuppressFBWarnings("OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE")
    public void saveDepartment(Department department) {

        try ( Connection connection = DriverManager.getConnection(url, username, password);
              PreparedStatement preparedStatement = connection.prepareStatement(saveDepartment, Statement.RETURN_GENERATED_KEYS);
              ) {

            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Override
    @SuppressFBWarnings("OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE")
    public void deleteDepartment(Integer id) {
        try ( Connection connection = DriverManager.getConnection(url, username, password);
              PreparedStatement preparedStatement = connection.prepareStatement(deleteDepartment);
                ) {

            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    @SuppressFBWarnings("OBL_UNSATISFIED_OBLIGATION_EXCEPTION_EDGE")
    public void updateDepartment(Integer id, String name) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateDepartment);
                ) {

            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Float getAverageSalaryInDepartment(Integer id) {
        return null;
    }

}
