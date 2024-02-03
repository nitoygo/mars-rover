package org.nasa.marsrover.rover.core.application.types;

public enum MoveCode {

    FORWARD ("M"),

    LEFT ("L"),

    RIGHT ("R");

    public final String value;

    MoveCode(String value) {
        this.value = value;
    }

    public static MoveCode fromString(String value) {
        for (MoveCode move : MoveCode.values()) {
            if (move.value.equalsIgnoreCase(value)) {
                return move;
            }
        }
        throw new IllegalArgumentException("No such move type: " + value);
    }

}
