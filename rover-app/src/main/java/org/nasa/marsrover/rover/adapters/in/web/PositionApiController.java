package org.nasa.marsrover.rover.adapters.in.web;

import lombok.AllArgsConstructor;
import org.nasa.marsrover.common.annotations.Adapter;
import org.nasa.marsrover.common.types.data.Coordinates;
import org.nasa.marsrover.common.types.data.Orientation;
import org.nasa.marsrover.common.types.data.Terrain;
import org.nasa.marsrover.common.types.messaging.CommandData;
import org.nasa.marsrover.common.types.messaging.ResponseData;
import org.nasa.marsrover.rover.core.ports.in.GetPositionUseCase;
import org.nasa.marsrover.rover.core.ports.in.SetPositionUseCase;
import org.nasa.marsrover.rover.core.ports.in.types.Position;
import org.nasa.marsrover.rover.core.ports.in.types.PositionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Adapter
@RestController
@AllArgsConstructor
@RequestMapping("/position")
public class PositionApiController {

    private static final Logger logger = LoggerFactory.getLogger(PositionApiController.class);

    private static final String POSITION_REGEX = "\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*([NnEeWwSs])\\s*";

    private static final Pattern POSITION_PATTERN = Pattern.compile(POSITION_REGEX);


    private final GetPositionUseCase getPositionUseCase;

    private final SetPositionUseCase setPositionUseCase;

    @PostMapping
    public ResponseEntity<Void> setPosition(@RequestBody CommandData command) {
        sendPositionCommand(command.data());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseData> getPosition() {
        String message = queryPosition();

        return new ResponseEntity<>(
                new ResponseData(message),
                HttpStatus.OK);
    }

    private void sendPositionCommand(String command) {
        Matcher matcher = POSITION_PATTERN.matcher(command);
        if (matcher.matches()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            String facing = matcher.group(3).toUpperCase();

            // TODO: better to implement a way to customize the map
            Terrain plateau = new Terrain(5, 5);

            try {
                setPositionUseCase.setPosition(new PositionCommand(
                        new Position(
                                new Coordinates(x, y),
                                Orientation.fromString(facing),
                                plateau)));
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid data: " + command);
            }
        } else {
            throw new IllegalArgumentException("Invalid data: " + command);
        }
    }

    private String queryPosition() {
        Position position = getPositionUseCase.getPositionQuery();
        return position.location().x() + "," +
                position.location().y() + "," +
                position.facing();
    }

}
