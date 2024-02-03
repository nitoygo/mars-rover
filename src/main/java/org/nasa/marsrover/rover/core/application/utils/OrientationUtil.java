package org.nasa.marsrover.rover.core.application.utils;

import org.nasa.marsrover.rover.common.types.Orientation;

public class OrientationUtil {

    public static Orientation turnLeft(Orientation currentOrientation) {
        return switch (currentOrientation) {
            case FACING_NORTH -> Orientation.FACING_WEST;
            case FACING_EAST -> Orientation.FACING_NORTH;
            case FACING_WEST -> Orientation.FACING_SOUTH;
            case FACING_SOUTH -> Orientation.FACING_EAST;
        };
    }

    public static Orientation turnRight(Orientation currentOrientation) {
        return switch (currentOrientation) {
            case FACING_NORTH -> Orientation.FACING_EAST;
            case FACING_EAST -> Orientation.FACING_SOUTH;
            case FACING_WEST -> Orientation.FACING_NORTH;
            case FACING_SOUTH -> Orientation.FACING_WEST;
        };
    }

}
