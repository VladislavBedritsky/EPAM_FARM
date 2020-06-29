package org.example.epam.backend.validator;

import org.example.epam.backend.annotation.DepartmentExistsInDB;
import org.example.epam.backend.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator which used with @DepartmentExistsInDB.
 * Return true if Department name is exists in DB.
 *
 * @version 1.01 29 Jun 2020
 * @author Uladzislau Biadrytski
 */
public class DepartmentsNameValidator implements
        ConstraintValidator<DepartmentExistsInDB, String> {

    @Autowired
    private DepartmentService departmentService;

    @Override
    public void initialize(DepartmentExistsInDB constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return departmentService.findAll()
                .stream()
                .anyMatch(department ->
                        department.getName().equals(s)
                );
    }
}
