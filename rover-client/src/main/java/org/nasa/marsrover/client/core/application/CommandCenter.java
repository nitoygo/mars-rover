package org.nasa.marsrover.client.core.application;

import lombok.RequiredArgsConstructor;
import org.nasa.marsrover.common.annotations.Service;
import org.nasa.marsrover.client.core.application.data.RoverClientConfig;
import org.nasa.marsrover.common.types.messaging.CommandData;
import org.nasa.marsrover.client.core.ports.TransmitCommandPort;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;


@Service
@RequiredArgsConstructor
public class CommandCenter implements CommandLineRunner {

    private static final String EXIT_KEYWORD = "EXIT";

    private final TransmitCommandPort transmitCommandPort;

    private final RoverClientConfig roverClientConfig;

    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Starting command line interface...");
            System.out.println("Type 'exit' to end)");

            String userInput = "";
            while (!userInput.equalsIgnoreCase(EXIT_KEYWORD)) {
                if (scanner.hasNextLine()) {
                    System.out.print(">> ");
                    userInput = scanner.nextLine();

                    transmitCommandPort.transmitCommand(
                            roverClientConfig.getServerUrl(),
                            roverClientConfig.getCommandApiEndpoint(),
                            new CommandData(userInput));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
