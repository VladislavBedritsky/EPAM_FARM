package org.example.EPAM_FARM.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.EPAM_FARM.json_view.View;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    @JsonView(View.Id.class)
    private Integer id;

    @JsonView(View.FullEmployeesWithoutDepartment.class)
    private String name;

    @JsonView(View.FullEmployeesWithoutDepartment.class)
    private LocalDate birthday;

    @JsonView(View.FullEmployeesWithoutDepartment.class)
    private Float salary;
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
