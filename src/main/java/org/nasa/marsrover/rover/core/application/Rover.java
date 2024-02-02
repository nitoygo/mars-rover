package org.nasa.marsrover.rover.core.application;

import lombok.Getter;
import lombok.Setter;
import org.nasa.marsrover.rover.common.types.Coordinates;
import org.nasa.marsrover.rover.common.types.Orientation;
import org.nasa.marsrover.rover.common.types.Terrain;

@Setter
@Getter
public class Rover {

    private Coordinates location;

    private Orientation facing;

    private Terrain map;

    private static Rover instance;

    private Rover() {
        location = new Coordinates(0, 0);
        facing = Orientation.NORTH;
    }

    public static Rover getInstance() {
        if (instance == null) {
            instance = new Rover();
        }
        return instance;
    }

    public void moveForward() {
        if (map == null) {
            throw new RuntimeException("No known map information.");
        }

        int x = location.x();
        int y = location.y();

        if (facing == Orientation.NORTH) {
            y++;
        } else if (facing == Orientation.EAST) {
            x++;
        } else if (facing == Orientation.WEST) {
            x--;
        } else if (facing == Orientation.SOUTH) {
            y--;
        }

        Coordinates newLocation = new Coordinates(x, y);
        if (map.isPassable(newLocation)) {
            this.location = newLocation;
        }
    }

    public void faceLeft() {
        Orientation newOrientation;

        if (facing == Orientation.NORTH) {
            newOrientation = Orientation.WEST;
        } else if (facing == Orientation.WEST) {
            newOrientation = Orientation.SOUTH;
        } else if (facing == Orientation.SOUTH) {
            newOrientation = Orientation.EAST;
        } else { // facing == Orientation.EAST
            newOrientation = Orientation.NORTH;
        }

        facing = newOrientation;
    }

    public void faceRight() {
        Orientation newOrientation;

        if (facing == Orientation.NORTH) {
            newOrientation = Orientation.EAST;
        } else if (facing == Orientation.EAST) {
            newOrientation = Orientation.SOUTH;
        } else if (facing == Orientation.SOUTH) {
            newOrientation = Orientation.WEST;
        } else { // facing == Orientation.WEST
            newOrientation = Orientation.NORTH;
        }

        facing = newOrientation;
    }

}
