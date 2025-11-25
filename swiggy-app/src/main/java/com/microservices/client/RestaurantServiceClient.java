package com.microservices.client;

import com.microservices.dto.OrderResponseDTO;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Date;

@Component
public class RestaurantServiceClient {
  /*  @Autowired
    private RestTemplate template;*/

    @Autowired
    private WebClient webClient;

    private final String SWIGGI_APP="swiggiApp";

    @Retry(name =SWIGGI_APP,fallbackMethod = "fallbackForApiCall")
    public OrderResponseDTO fetchOrderStatus(String orderId) {
      return   webClient
              .get()
              .uri("http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId)
              .retrieve().bodyToMono(OrderResponseDTO.class).block();
        //return template.getForObject("http://RESTAURANT-SERVICE/restaurant/orders/status/" + orderId, OrderResponseDTO.class);
    }
    public OrderResponseDTO fallbackForApiCall(Exception t) {
        System.out.println("Fallback method executed due to: " + t.getMessage());
        return new OrderResponseDTO("123","test",2,20.0,new Date(System.currentTimeMillis()),"aprroved",1);
    }
}
