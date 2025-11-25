package com.microservices.client;

import com.microservices.dto.OrderResponseDTO;
import com.microservices.exception.SwiggyServiceException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Date;

@Component
@Slf4j
public class RestaurantServiceClient {
  /*  @Autowired
    private RestTemplate template;*/

    @Autowired
    private WebClient webClient;

    private final String SWIGGI_APP = "swiggiApp";

    //@Retry(name = SWIGGI_APP, fallbackMethod = "fallbackForApiCall")   --- to check global exception handling
    public OrderResponseDTO fetchOrderStatus(String orderId) {
        OrderResponseDTO orderResponseDTO = null;

        try {
            orderResponseDTO = webClient
                    .get()
                    .uri("http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId)
                    .retrieve().bodyToMono(OrderResponseDTO.class).block();

            //return template.getForObject("http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId, OrderResponseDTO.class);

        } catch (Exception errorException) {
            log.error("RestaurantServiceClient::fetchOrderStatus caught the HttpServer server error {}", errorException.getMessage());
            throw new SwiggyServiceException(errorException.getMessage());
        }
        return orderResponseDTO;
    }

    public OrderResponseDTO fallbackForApiCall(Exception t) {
        System.out.println("Fallback method executed due to: " + t.getMessage());
        return new OrderResponseDTO("123", "test", 2, 20.0, new Date(System.currentTimeMillis()), "aprroved", 1);
    }
}
