package org.nasa;

import org.nasa.marsrover.rover.adapters.in.cli.CommandLineInterface;
import org.nasa.marsrover.rover.common.types.Coordinates;
import org.nasa.marsrover.rover.common.types.Orientation;
import org.nasa.marsrover.rover.common.types.Position;
import org.nasa.marsrover.rover.common.types.Terrain;
import org.nasa.marsrover.rover.core.application.ManeuverService;
import org.nasa.marsrover.rover.core.application.PositionService;
import org.nasa.marsrover.rover.core.ports.in.ManeuverUseCase;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PositionService positionService = new PositionService();
        ManeuverUseCase maneuverUseCase = new ManeuverService();

        // Create map
        Terrain noMansLand = new Terrain(5, 5);

        // Place rover on initial position
        positionService.setPosition(new Position(
                new Coordinates(0,0),
                Orientation.NORTH,
                noMansLand
        ));

        CommandLineInterface cli = new CommandLineInterface(
                positionService,
                maneuverUseCase
        );

        cli.run(args);
    }
}
