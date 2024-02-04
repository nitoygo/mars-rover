package org.nasa.marsrover.rover;

import org.nasa.marsrover.rover.core.application.Rover;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public Rover rover(){
        return new Rover();
    }

}
