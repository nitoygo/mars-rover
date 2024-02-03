package org.nasa.marsrover.rover.adapters.in.cli;

import lombok.AllArgsConstructor;
import org.nasa.marsrover.rover.common.types.Coordinates;
import org.nasa.marsrover.rover.common.types.Orientation;
import org.nasa.marsrover.rover.core.ports.in.types.Position;
import org.nasa.marsrover.rover.common.types.Terrain;
import org.nasa.marsrover.rover.core.ports.in.GetPositionUseCase;
import org.nasa.marsrover.rover.core.ports.in.types.ManeuverCommand;
import org.nasa.marsrover.rover.core.ports.in.ManeuverUseCase;
import org.nasa.marsrover.rover.core.ports.in.SetPositionUseCase;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class CommandLineInterface {

    private static final String EXIT_KEYWORD = "EXIT";

    private static final String POSITION_REGEX = "\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*([NnEeWwSs])\\s*";

    private static final Pattern POSITION_PATTERN = Pattern.compile(POSITION_REGEX);


    private final GetPositionUseCase getPositionUseCase;

    private final SetPositionUseCase setPositionUseCase;

    private final ManeuverUseCase maneuverUseCase;

    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Starting command line chat interface...");
            System.out.println("Type 'exit' to end)");

            String input = "";
            while (!input.equalsIgnoreCase(EXIT_KEYWORD)) {
                System.out.print(">> ");
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine();
                    handleInput(input);
                }
            }
        } catch (Exception e) {
            System.out.println("CLI Error: " + e.getMessage());
        }
    }

    private void handleInput(String input) {
        if (isPositionCommand(input)) {
            // not explicitly required but good to have a command
            // to teleport the rover (for testing purposes)
            sendPositionCommand(input);
        } else {
            sendManeuverCommand(input);
        }
    }

    private boolean isPositionCommand(String input) {
        Matcher matcher = POSITION_PATTERN.matcher(input);
        return matcher.matches();
    }

    private void sendPositionCommand(String input) {
        Matcher matcher = POSITION_PATTERN.matcher(input);
        if (matcher.matches()) {

            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            String facing = matcher.group(3).toUpperCase();

            // TODO: better to implement a way to customize the map
            Terrain plateau = new Terrain(5, 5);

            try {
                setPositionUseCase.setPosition(new Position(
                        new Coordinates(x, y),
                        Orientation.fromString(facing),
                        plateau
                ));
            } catch (Exception e) {
                System.out.println("Position command error: " + e.getMessage());
            }
        }
    }

    private void sendManeuverCommand(String input) {
        try {
            // send instructions to rover
            maneuverUseCase.maneuver(new ManeuverCommand(input));

            // retrieve rover position
            Position position = getPositionUseCase.getPosition();
            System.out.println(
                    position.location().x() + "," +
                            position.location().y() + "," +
                            position.facing());

        } catch (Exception e) {
            System.out.println("Maneuver command error: " + e.getMessage());
        }
    }

}
