package org.nasa.marsrover.rover.core.ports.in.types;

import org.nasa.marsrover.common.types.data.Coordinates;
import org.nasa.marsrover.common.types.data.Terrain;
import org.nasa.marsrover.common.types.data.Orientation;

public record Position(
        Coordinates location,

        Orientation facing,

        Terrain terrain
) {}
