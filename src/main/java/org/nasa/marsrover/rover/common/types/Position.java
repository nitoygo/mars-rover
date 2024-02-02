package org.nasa.marsrover.rover.common.types;

public record Position(
        Coordinates location,

        Orientation facing,

        Terrain terrain
) {}
