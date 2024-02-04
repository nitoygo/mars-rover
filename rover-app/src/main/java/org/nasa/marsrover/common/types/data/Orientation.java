package org.nasa.marsrover.common.types.data;

public enum Orientation {

    FACING_NORTH("N"),
    FACING_EAST("E"),
    FACING_WEST("W"),
    FACING_SOUTH("S");

    public final String value;

    Orientation(String value) {
        this.value = value;
    }

    public static Orientation fromString(String value) {
        for (Orientation orientation : Orientation.values()) {
            if (orientation.value.equalsIgnoreCase(value)) {
                return orientation;
            }
        }
        throw new IllegalArgumentException("No such orientation: " + value);
    }

    @Override
    public String toString() {
        return this.value;
    }

}
