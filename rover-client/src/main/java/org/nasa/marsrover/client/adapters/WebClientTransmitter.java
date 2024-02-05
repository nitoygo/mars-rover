package org.nasa.marsrover.client.adapters;

import lombok.RequiredArgsConstructor;
import org.nasa.marsrover.common.annotations.Adapter;
import org.nasa.marsrover.common.types.messaging.CommandData;
import org.nasa.marsrover.client.core.ports.CommunicationPort;
import org.nasa.marsrover.common.types.messaging.QueryData;
import org.nasa.marsrover.common.types.messaging.ResponseData;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Adapter
@RequiredArgsConstructor
public class WebClientTransmitter implements CommunicationPort {

    private static final Logger logger = LoggerFactory.getLogger(WebClientTransmitter.class);

    private final RestTemplate restTemplate;

    @Override
    public void sendCommand(String server, String api, String command) {
        String apiUrl = server + api;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<CommandData> requestEntity = new HttpEntity<>(
                    new CommandData(command), headers);

            // Make a POST request to the rover api
            restTemplate.postForEntity(apiUrl, requestEntity, ResponseData.class);
        } catch (HttpClientErrorException e) {
            // Handle client errors (4xx)
            logger.warn("Client error[{}]: {} - {}", apiUrl, e.getRawStatusCode(), e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            // Handle server errors (5xx)
            logger.warn("Server error[{}]: {} - {}", apiUrl, e.getRawStatusCode(), e.getResponseBodyAsString());
        }
    }

    @Override
    public String sendQuery(String server, String api, String query) {
        String apiUrl = server + api;

        try {
            // Make a GET request to the rover's api
            // Note: query is unused for now

            ResponseEntity<ResponseData> responseEntity = restTemplate.getForEntity(apiUrl, ResponseData.class);

            ResponseData responseData = responseEntity.getBody();
            if (responseData != null) {
                return responseData.message();
            }

        } catch (HttpClientErrorException e) {
            // Handle client errors (4xx)
            logger.warn("Client error[{}]: {} - {}", apiUrl, e.getRawStatusCode(), e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            // Handle server errors (5xx)
            logger.warn("Server error[{}]: {} - {}", apiUrl, e.getRawStatusCode(), e.getResponseBodyAsString());
        }

        return "No response";
    }

}
