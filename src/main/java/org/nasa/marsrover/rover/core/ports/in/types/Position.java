package org.nasa.marsrover.rover.core.ports.in.types;

import org.nasa.marsrover.rover.common.types.Coordinates;
import org.nasa.marsrover.rover.common.types.Terrain;
import org.nasa.marsrover.rover.common.types.Orientation;

public record Position(
        Coordinates location,

        Orientation facing,

        Terrain terrain
) {}
