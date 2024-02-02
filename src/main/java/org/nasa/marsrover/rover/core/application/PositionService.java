package org.nasa.marsrover.rover.core.application;

import org.nasa.marsrover.rover.common.types.Position;
import org.nasa.marsrover.rover.core.ports.in.GetPositionUseCase;
import org.nasa.marsrover.rover.core.ports.in.SetPositionUseCase;

public class PositionService implements GetPositionUseCase, SetPositionUseCase {

    private final Rover rover = Rover.getInstance();

    @Override
    public Position getPosition() {
        return new Position(
                rover.getLocation(),
                rover.getFacing(),
                rover.getMap()
        );
    }

    @Override
    public void setPosition(Position position) {
        rover.setLocation(position.location());
        rover.setFacing(position.facing());
        rover.setMap(position.terrain());
    }

}
