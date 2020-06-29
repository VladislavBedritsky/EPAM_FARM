package org.example.epam.backend.annotation;

import org.example.epam.backend.validator.DepartmentsNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation which validates by DepartmentsNameValidator
 * and used with Department name field type String
 *
 * @version 1.01 29 Jun 2020
 * @author Uladzislau Biadrytski
 */
@Documented
@Constraint(validatedBy = DepartmentsNameValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DepartmentExistsInDB {
    String message() default "Such Department is not exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
