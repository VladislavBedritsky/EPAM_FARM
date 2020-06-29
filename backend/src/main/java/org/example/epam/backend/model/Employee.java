package org.example.epam.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.example.epam.backend.json_view.View;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

/**
 * POJO Employee
 *
 * @version 1.01 02 Feb 2020
 * @author Uladzislau Biadrytski
 */
public class Employee {

    @JsonView(View.Id.class)
    private Integer id;
    @NotBlank(message = "Employee name can't be empty")
    @JsonView(View.FullEmployeesWithoutDepartment.class)
    private String name;
    @Past(message = "Employee birthday should be before today")
    @NotNull(message = "Employee birthday can't be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView(View.FullEmployeesWithoutDepartment.class)
    private LocalDate birthday;
    @NotNull(message = "Employee salary can't be null")
    @JsonView(View.FullEmployeesWithoutDepartment.class)
    private Float salary;

    @Valid
    private Department department;

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(birthday, employee.birthday) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday, salary, department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}
