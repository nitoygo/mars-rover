package org.nasa.marsrover.client;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "roverclient")
public class RoverClientConfigProperties {

    private String serverUrl;
    private String maneuverApi;
    private String positionApi;
    
}
