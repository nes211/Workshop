package pl.tdelektro.workshop.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = VinValidator.class)
public @interface Vin {

    String message() default "Vin invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};



}
