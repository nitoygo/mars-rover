package org.nasa.marsrover.client;

import org.nasa.marsrover.client.core.application.data.RoverClientConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RoverClientConfigProperties.class)
public class ApplicationConfig {

    @Bean
    public RoverClientConfig roverClientConfig(
            RoverClientConfigProperties roverClientConfigProperties){
        return new RoverClientConfig(
                roverClientConfigProperties.getServerUrl(),
                roverClientConfigProperties.getCommandApiEndpoint());
    }

}
