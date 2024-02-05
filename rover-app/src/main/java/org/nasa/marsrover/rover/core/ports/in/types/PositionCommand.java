package org.nasa.marsrover.rover.core.ports.in.types;

import jakarta.validation.constraints.NotNull;

public record PositionCommand (
        @NotNull
        Position position
) {}
