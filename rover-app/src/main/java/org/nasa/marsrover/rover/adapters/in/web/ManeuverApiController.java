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
import org.nasa.marsrover.rover.core.ports.in.types.PositionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Adapter
@RestController
@AllArgsConstructor
public class ManeuverApiController {

    private static final Logger logger = LoggerFactory.getLogger(ManeuverApiController.class);

    private final ManeuverUseCase maneuverUseCase;

    @PostMapping(path = "/maneuver")
    public ResponseEntity<Void> maneuver(@RequestBody CommandData command) {
        sendManeuverCommand(command.data());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void sendManeuverCommand(String input) {
        try {
            maneuverUseCase.maneuver(new ManeuverCommand(input));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid data: " + input);
        }
    }

}
