package org.nasa.marsrover.rover.core.application;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.nasa.marsrover.common.annotations.UseCase;
import org.nasa.marsrover.rover.core.ports.in.types.Position;
import org.nasa.marsrover.rover.core.ports.in.GetPositionUseCase;
import org.nasa.marsrover.rover.core.ports.in.SetPositionUseCase;
import org.nasa.marsrover.rover.core.ports.in.types.PositionCommand;

@UseCase
@AllArgsConstructor
public class PositionService implements GetPositionUseCase, SetPositionUseCase {

    @Setter
    private Rover rover;

    @Override
    public Position getPositionQuery() {
        return new Position(
                rover.getLocation(),
                rover.getFacing(),
                rover.getMap()
        );
    }

    @Override
    public void setPosition(@NotNull PositionCommand command) {
        rover.setLocation(command.position().location());
        rover.setFacing(command.position().facing());
        rover.setMap(command.position().terrain());
    }

}
