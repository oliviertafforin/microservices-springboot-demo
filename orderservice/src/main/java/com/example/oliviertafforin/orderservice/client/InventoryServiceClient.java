package com.example.oliviertafforin.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String url;

    public ResponseEntity<Void> updateEventCapacity(final Long eventId, final Long ticketsBooked){
        final RestTemplate restTemplate = new RestTemplate();

        restTemplate.put(url + "/events/"+eventId+"/capacity/"+ticketsBooked, null);
        return ResponseEntity.ok().build();
    }
}
