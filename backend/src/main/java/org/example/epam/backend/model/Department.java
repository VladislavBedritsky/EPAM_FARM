package org.example.epam.backend.model;

import org.example.epam.backend.annotation.DepartmentExistsInDB;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * POJO Department
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public class Department {

    private Integer id;
    @DepartmentExistsInDB
    @NotBlank(message = "Department name can't be empty")
    private String name;

    public Department() {}

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
