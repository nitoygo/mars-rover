package org.nasa.marsrover.rover.core.application;

import lombok.Getter;
import lombok.Setter;
import org.nasa.marsrover.rover.common.types.Coordinates;
import org.nasa.marsrover.rover.common.types.Orientation;
import org.nasa.marsrover.rover.common.types.Terrain;
import org.nasa.marsrover.rover.core.application.utils.OrientationUtil;

@Setter
@Getter
public class Rover {

    private Coordinates location;

    private Orientation facing;

    private Terrain map;

    private static Rover instance;

    private Rover() {
        location = new Coordinates(0, 0);
        facing = Orientation.FACING_NORTH;
    }

    public static Rover getInstance() {
        if (instance == null) {
            instance = new Rover();
        }
        return instance;
    }

    public void faceLeft() {
        // make the rover's body face left
        facing = OrientationUtil.turnLeft(facing);
    }

    public void faceRight() {
        // make the rover's body face right
        facing = OrientationUtil.turnRight(facing);
    }

    public void moveForward() {
        // make the rover move one unit forward

        if (map == null) {
            throw new RuntimeException("No known map information.");
        }

        int x = location.x();
        int y = location.y();

        if (facing == Orientation.FACING_NORTH) {
            y++;
        } else if (facing == Orientation.FACING_EAST) {
            x++;
        } else if (facing == Orientation.FACING_WEST) {
            x--;
        } else if (facing == Orientation.FACING_SOUTH) {
            y--;
        }

        Coordinates newLocation = new Coordinates(x, y);
        if (map.isPassable(newLocation)) {
            this.location = newLocation;
        }
    }

}
