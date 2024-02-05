package org.nasa.marsrover.client.core.application.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoverClientConfig {

    private static final String DEFAULT_SERVER_URL = "localhost:8080";

    private static final String DEFAULT_COMMAND_ENDPOINT = "/";

    private String serverUrl = DEFAULT_SERVER_URL;
    private String maneuverApi = DEFAULT_COMMAND_ENDPOINT;
    private String positionApi = DEFAULT_COMMAND_ENDPOINT;

}
