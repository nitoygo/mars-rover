package org.nasa.marsrover.client.core.ports;

import org.nasa.marsrover.common.types.messaging.CommandData;

public interface TransmitCommandPort {

    void transmitCommand(String server, String api, CommandData commandData);

}
