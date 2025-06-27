package com.example.oliviertafforin.bookingservice.client;

import com.example.oliviertafforin.bookingservice.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String url;

    public InventoryResponse getInventory(final Long eventId){
        final RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url + "/events/"+eventId, InventoryResponse.class);
    }
}
