package org.nasa.marsrover.rover.common.types;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Terrain {

    public static final int IMPASSABLE = -1;

    protected int ySize;

    protected int xSize;

    // contains information whether an x,y location is passable or not
    protected int[][] grid;

    // name of the area the rover is currently on (ex: 'Wastelands 001')
    // protected String id;

    public Terrain(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

        if (ySize < 1 || xSize < 1) {
            throw new IllegalArgumentException(
                    "Invalid map area provided: [" + ySize + "," + xSize + "]");
        }

        grid = new int[ySize][xSize];
    }

    public Terrain(int[][] newArea) {
        ySize = newArea.length;
        xSize = (ySize > 0) ? newArea[0].length : 0;

        if (ySize < 1 || xSize < 1) {
            throw new IllegalArgumentException(
                    "Invalid map area provided: [" + ySize + "," + xSize + "]");
        }

        this.grid = newArea;
    }

    public boolean isPassable(Coordinates coordinates) {
        int x = coordinates.x();
        int y = coordinates.y();

        if ((x >= 0 && x < xSize) && (y >= 0 && y < ySize)) {
            return grid[y][x] != IMPASSABLE;
        }

        return false;
    }

}
