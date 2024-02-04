package org.nasa.marsrover.rover.core.ports.in.types;

import jakarta.validation.constraints.NotNull;

public record ManeuverCommand(
        @NotNull String instruction
) {
    public ManeuverCommand(String instruction) {
        // Note: consider using bean validation api
        // For now, just ensure that the command accepts valid letters
        if (!instruction.matches("^[MLR]*$")) {
            throw new IllegalArgumentException("Invalid instruction syntax: " + instruction);
        }

        this.instruction = instruction;
    }

}
