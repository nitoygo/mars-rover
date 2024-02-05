package org.nasa.marsrover.rover.core.ports.in.types;

import jakarta.validation.constraints.NotNull;

import static org.nasa.marsrover.common.validation.Validation.validate;

public record ManeuverCommand(
        @NotNull
        @ValidManeuverInstruction
        String instruction
) {
    public ManeuverCommand(String instruction) {
        this.instruction = instruction;
        validate(this);
    }

}
