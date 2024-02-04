package org.nasa.marsrover.rover.adapters.in.web;

import lombok.AllArgsConstructor;
import org.nasa.marsrover.common.annotations.Adapter;
import org.nasa.marsrover.common.types.data.Coordinates;
import org.nasa.marsrover.common.types.data.Orientation;
import org.nasa.marsrover.common.types.messaging.CommandData;
import org.nasa.marsrover.common.types.messaging.ResponseData;
import org.nasa.marsrover.rover.core.ports.in.types.Position;
import org.nasa.marsrover.common.types.data.Terrain;
import org.nasa.marsrover.rover.core.ports.in.GetPositionUseCase;
import org.nasa.marsrover.rover.core.ports.in.types.ManeuverCommand;
import org.nasa.marsrover.rover.core.ports.in.ManeuverUseCase;
import org.nasa.marsrover.rover.core.ports.in.SetPositionUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Adapter
@RestController
@AllArgsConstructor
public class RoverApiController {

    private static final Logger logger = LoggerFactory.getLogger(RoverApiController.class);

    private static final String POSITION_REGEX = "\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*([NnEeWwSs])\\s*";

    private static final Pattern POSITION_PATTERN = Pattern.compile(POSITION_REGEX);


    private final GetPositionUseCase getPositionUseCase;

    private final SetPositionUseCase setPositionUseCase;

    private final ManeuverUseCase maneuverUseCase;

    @PostMapping(path = "/command")
    public ResponseEntity<ResponseData> sendCommand(@RequestBody CommandData command) {
        String message;

        if (isPositionCommand(command.instruction())) {
            // not explicitly required but good to have a command
            // to teleport the rover (for testing purposes)
            message = sendPositionCommand(command.instruction());
        } else {
            message = sendManeuverCommand(command.instruction());
        }

        return new ResponseEntity<>(
                new ResponseData(message),
                HttpStatus.OK);
    }

    private boolean isPositionCommand(String command) {
        Matcher matcher = POSITION_PATTERN.matcher(command);
        return matcher.matches();
    }

    private String sendPositionCommand(String command) {
        String message;

        Matcher matcher = POSITION_PATTERN.matcher(command);
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

                // retrieve rover position (should be the same as input really)
                message = queryPosition();
            } catch (Exception e) {
                message = "Position command error: " + e.getMessage();
                logger.warn(message);
            }
        } else {
            message = "Invalid command: " + command;
        }

        return message;
    }

    private String sendManeuverCommand(String input) {
        String message;
        try {
            // send instructions to rover
            maneuverUseCase.maneuver(new ManeuverCommand(input));

            // retrieve rover position
            message = queryPosition();

        } catch (Exception e) {
            message = "Maneuver command error: " + e.getMessage();
            logger.warn(message);
        }

        return message;
    }

    private String queryPosition() {
        Position position = getPositionUseCase.getPosition();
        return position.location().x() + "," +
                position.location().y() + "," +
                position.facing();
    }

}
