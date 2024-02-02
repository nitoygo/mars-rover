package org.nasa.marsrover.rover.common.types;

public enum Movement {

    FORWARD ("M"),

    LEFT ("L"),

    RIGHT ("R");

    public final String value;

    Movement(String value) {
        this.value = value;
    }

    public static Movement fromString(String value) {
        for (Movement move : Movement.values()) {
            if (move.value.equalsIgnoreCase(value)) {
                return move;
            }
        }
        throw new IllegalArgumentException("No such move type: " + value);
    }

}
