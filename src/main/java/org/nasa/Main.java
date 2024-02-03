package org.nasa;

import org.nasa.marsrover.rover.adapters.in.cli.CommandLineInterface;
import org.nasa.marsrover.rover.common.types.Coordinates;
import org.nasa.marsrover.rover.common.types.Orientation;
import org.nasa.marsrover.rover.core.ports.in.types.Position;
import org.nasa.marsrover.rover.common.types.Terrain;
import org.nasa.marsrover.rover.core.application.ManeuverService;
import org.nasa.marsrover.rover.core.application.PositionService;
import org.nasa.marsrover.rover.core.application.Rover;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Rover rover = Rover.getInstance();

        PositionService positionService = new PositionService();
        positionService.setRover(rover);

        ManeuverService maneuverService = new ManeuverService();
        maneuverService.setRover(rover);

        // Create map
        Terrain plateau = new Terrain(5, 5);

        // Place rover on initial position
        positionService.setPosition(new Position(
                new Coordinates(0,0),
                Orientation.FACING_NORTH,
                plateau
        ));

        CommandLineInterface cli = new CommandLineInterface(
                positionService,
                positionService,
                maneuverService
        );

        cli.run(args);
    }
}
