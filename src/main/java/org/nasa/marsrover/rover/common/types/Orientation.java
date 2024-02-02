package org.nasa.marsrover.rover.common.types;

public enum Orientation {

    NORTH ("N"),
    EAST ("E"),
    WEST ("W"),
    SOUTH ("S");

    public final String value;

    Orientation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
