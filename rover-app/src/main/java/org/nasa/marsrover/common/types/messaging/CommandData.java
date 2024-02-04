package org.nasa.marsrover.common.types.messaging;

import jakarta.validation.constraints.NotNull;

public record CommandData (
        @NotNull String instruction
) {}
