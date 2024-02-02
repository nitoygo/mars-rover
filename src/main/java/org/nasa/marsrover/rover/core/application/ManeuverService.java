package org.nasa.marsrover.rover.core.application;

import org.nasa.marsrover.rover.common.types.Movement;
import org.nasa.marsrover.rover.core.ports.in.ManeuverCommand;
import org.nasa.marsrover.rover.core.ports.in.ManeuverUseCase;

import java.util.ArrayList;
import java.util.List;

public class ManeuverService implements ManeuverUseCase {

    private final Rover rover = Rover.getInstance();

    @Override
    public void maneuver(ManeuverCommand command) {
        List<Movement> moves = getMoves(command.instruction());

        for (Movement move : moves) {
            if (move == Movement.FORWARD) {
                rover.moveForward();
            }
            else if (move == Movement.LEFT) {
                rover.faceLeft();
            }
            else if (move == Movement.RIGHT) {
                rover.faceRight();
            }
        }
    }

    private List<Movement> getMoves(String instruction) {
        String[] parsedMoves = instruction.split("");
        ArrayList<Movement> moveList = new ArrayList<>();

        for (String move : parsedMoves) {
            try {
                moveList.add(Movement.fromString(move));
            } catch(IllegalArgumentException ignore) {}
        }

        return moveList;
    }

}
