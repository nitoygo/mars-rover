package org.nasa.marsrover.rover.core.ports.in;

import org.nasa.marsrover.rover.core.ports.in.types.ManeuverCommand;

public interface ManeuverUseCase {

    void maneuver(ManeuverCommand command);

}
