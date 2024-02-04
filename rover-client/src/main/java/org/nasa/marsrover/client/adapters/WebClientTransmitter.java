package org.nasa.marsrover.client.adapters;

import lombok.RequiredArgsConstructor;
import org.nasa.marsrover.common.annotations.Adapter;
import org.nasa.marsrover.common.types.messaging.CommandData;
import org.nasa.marsrover.client.core.ports.TransmitCommandPort;
import org.nasa.marsrover.common.types.messaging.ResponseData;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Adapter
@RequiredArgsConstructor
public class WebClientTransmitter implements TransmitCommandPort {

    private static final Logger logger = LoggerFactory.getLogger(WebClientTransmitter.class);

    private final RestTemplate restTemplate;

    @Override
    public void transmitCommand(String server, String api, CommandData commandData) {
        String apiUrl = server + api;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<CommandData> requestEntity = new HttpEntity<>(
                    new CommandData(commandData.instruction()), headers);

            // Make a POST request to the rover api
            ResponseEntity<ResponseData> responseEntity = restTemplate.postForEntity(apiUrl, requestEntity, ResponseData.class);

            ResponseData responseData = responseEntity.getBody();
            if (responseData != null) {
                logger.info(responseData.message());
            }

        } catch (HttpClientErrorException e) {
            // Handle client errors (4xx)
            logger.warn("Client error: {} - {}", e.getRawStatusCode(), e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            // Handle server errors (5xx)
            logger.warn("Server error: {} - {}", e.getRawStatusCode(), e.getResponseBodyAsString());
        }

    }

}
