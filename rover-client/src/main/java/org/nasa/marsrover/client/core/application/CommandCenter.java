package org.nasa.marsrover.client.core.application;

import lombok.RequiredArgsConstructor;
import org.nasa.marsrover.common.annotations.Service;
import org.nasa.marsrover.client.core.application.data.RoverClientConfig;
import org.nasa.marsrover.client.core.ports.CommunicationPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import java.util.Scanner;


@Service
@RequiredArgsConstructor
public class CommandCenter implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CommandCenter.class);
    private static final String EXIT_KEYWORD = "EXIT";

    private final CommunicationPort communicationPort;

    private final RoverClientConfig roverClientConfig;

    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Starting command line interface...");
            System.out.println(">> Start by typing initial position (e.g. 0,0,N)");
            System.out.println(">> Type 'exit' to end)");

            while (true) {
                System.out.print(">> ");

                if (!scanner.hasNextLine()) {
                    break;
                }

                String input = (scanner.nextLine()).trim();
                if (input.equalsIgnoreCase(EXIT_KEYWORD)) {
                    break;
                }

                handleInput(input);
            }
        } catch (Exception e) {
            logger.error("CLI Error: {}", e.getMessage());
        }
    }

    private void handleInput(String input) {
        try {
            logger.info(input);

            // TODO: consider using keywords to distinguish between commands
            if (input.matches("^[MLR]*$")) {
                communicationPort.sendCommand(
                        roverClientConfig.getServerUrl(),
                        roverClientConfig.getManeuverApi(),
                        input);

                String response = communicationPort.sendQuery(
                        roverClientConfig.getServerUrl(),
                        roverClientConfig.getPositionApi(),
                        // This is currently unused so just pass dummy for now
                        "");

                logger.info(response);
            } else {
                communicationPort.sendCommand(
                        roverClientConfig.getServerUrl(),
                        roverClientConfig.getPositionApi(),
                        input);
            }
        } catch (Exception e) {
            logger.error("Transmit Error: {}", e.getMessage());
        }
    }

}
