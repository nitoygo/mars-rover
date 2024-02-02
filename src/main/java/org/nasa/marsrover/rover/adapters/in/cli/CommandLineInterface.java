package org.nasa.marsrover.rover.adapters.in.cli;

import lombok.AllArgsConstructor;
import org.nasa.marsrover.rover.common.types.Position;
import org.nasa.marsrover.rover.core.ports.in.GetPositionUseCase;
import org.nasa.marsrover.rover.core.ports.in.ManeuverCommand;
import org.nasa.marsrover.rover.core.ports.in.ManeuverUseCase;

import java.util.Scanner;

@AllArgsConstructor
public class CommandLineInterface {

    private static final String EXIT_KEYWORD = "EXIT";

    private final GetPositionUseCase getPositionUseCase;

    private final ManeuverUseCase maneuverUseCase;

    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Starting command line chat interface...");
            System.out.println("Type 'exit' to end)");

            String instruction = "";
            while (!instruction.equalsIgnoreCase(EXIT_KEYWORD)) {
                System.out.print(">> ");
                if (scanner.hasNextLine()) {
                    instruction = scanner.nextLine();

                    // Note: Consider adding keywords for setting up the rover (e.g. initial location)

                    // send instructions to rover
                    maneuverUseCase.maneuver(new ManeuverCommand(instruction));

                    // retrieve rover position
                    Position position = getPositionUseCase.getPosition();
                    System.out.println(
                            position.location().x() + "," +
                                    position.location().y() + "," +
                                    position.facing());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
