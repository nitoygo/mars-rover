package org.nasa.marsrover.rover.core.ports.in.types;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ManeuverInstructionValidator
        implements ConstraintValidator<ValidManeuverInstruction, String> {

    @Override
    public boolean isValid(String instruction, ConstraintValidatorContext constraintValidatorContext) {
        return instruction.matches("^[MLR]*$");
    }

}
