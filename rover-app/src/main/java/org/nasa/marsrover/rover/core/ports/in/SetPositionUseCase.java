package org.nasa.marsrover.rover.core.ports.in;

import org.nasa.marsrover.rover.core.ports.in.types.Position;
import org.nasa.marsrover.rover.core.ports.in.types.PositionCommand;

public interface SetPositionUseCase {

    void setPosition(PositionCommand positionCommand);

}
