package org.nasa.marsrover.client.core.ports;

import org.nasa.marsrover.common.types.messaging.CommandData;
import org.nasa.marsrover.common.types.messaging.QueryData;

public interface CommunicationPort {

    void sendCommand(String server, String api, String command);

    String sendQuery(String server, String api, String query);

}
