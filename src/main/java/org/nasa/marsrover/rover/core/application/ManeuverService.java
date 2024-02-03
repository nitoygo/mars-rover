package org.nasa.marsrover.rover.core.application;

import lombok.Setter;
import org.nasa.marsrover.rover.core.application.types.MoveCode;
import org.nasa.marsrover.rover.core.ports.in.types.ManeuverCommand;
import org.nasa.marsrover.rover.core.ports.in.ManeuverUseCase;

import java.util.ArrayList;
import java.util.List;

public class ManeuverService implements ManeuverUseCase {

    @Setter
    private Rover rover;

    @Override
    public void maneuver(ManeuverCommand command) {
        List<MoveCode> moveCodes = getMoves(command.instruction());

        for (MoveCode code : moveCodes) {
            switch (code) {
                case FORWARD:
                    rover.moveForward();
                    break;
                case LEFT:
                    rover.faceLeft();
                    break;
                case RIGHT:
                    rover.faceRight();
                    break;
                // Handle any additional enum constants if needed
                default:
                    // Handle the default case or ignore if not needed
                    break;
            }
        }
    }

    private List<MoveCode> getMoves(String instruction) {
        String[] parsedMoves = instruction.split("");
        ArrayList<MoveCode> moveList = new ArrayList<>();

        for (String move : parsedMoves) {
            try {
                moveList.add(MoveCode.fromString(move));
            } catch(IllegalArgumentException ignore) {}
        }

        return moveList;
    }

}
