package org.nasa.marsrover.rover.core.ports.in.types;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ManeuverInstructionValidator.class)
@Documented
public @interface ValidManeuverInstruction {
    String message() default "can only be a combination of M|L|R" +
            " found: ${validatedValue}";

    Class<?>[] groups() default {};

    Class<? extends String>[] payload() default {};

}
