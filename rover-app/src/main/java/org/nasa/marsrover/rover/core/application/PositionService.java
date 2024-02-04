package org.nasa.marsrover.rover.core.application;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.nasa.marsrover.common.annotations.UseCase;
import org.nasa.marsrover.rover.core.ports.in.types.Position;
import org.nasa.marsrover.rover.core.ports.in.GetPositionUseCase;
import org.nasa.marsrover.rover.core.ports.in.SetPositionUseCase;

@UseCase
@AllArgsConstructor
public class PositionService implements GetPositionUseCase, SetPositionUseCase {

    @Setter
    private Rover rover;

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
