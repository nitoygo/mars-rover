package org.nasa.marsrover.rover.core.ports.in.types;

import jakarta.validation.constraints.NotNull;
import org.nasa.marsrover.common.types.data.Coordinates;
import org.nasa.marsrover.common.types.data.Terrain;
import org.nasa.marsrover.common.types.data.Orientation;

public record Position(
        @NotNull
        Coordinates location,

        @NotNull
        Orientation facing,

        @NotNull
        Terrain terrain
) {}
