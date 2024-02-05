package org.nasa.marsrover.rover.core.application;

import lombok.Getter;
import lombok.Setter;
import org.nasa.marsrover.common.types.data.Coordinates;
import org.nasa.marsrover.common.types.data.Orientation;
import org.nasa.marsrover.common.types.data.Terrain;
import org.nasa.marsrover.rover.core.application.utils.OrientationUtil;
import org.nasa.marsrover.rover.core.ports.in.types.InvalidStateException;
import org.springframework.stereotype.Component;

@Setter
@Getter
public class Rover {

    private Coordinates location;

    private Orientation facing;

    private Terrain map;

    public Rover() {
        location = new Coordinates(0, 0);
        facing = Orientation.FACING_NORTH;
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
            throw new InvalidStateException("No known map information.");
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
