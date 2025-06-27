package com.example.oliviertafforin.inventoryservice.controller;

import com.example.oliviertafforin.inventoryservice.dto.EventInventoryResponse;
import com.example.oliviertafforin.inventoryservice.dto.VenueInventoryResponse;
import com.example.oliviertafforin.inventoryservice.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/events")
    public @ResponseBody List<EventInventoryResponse> getAllInventoryEvents(){
        return inventoryService.getAllEvents();
    }

    @GetMapping("/events/{eventId}")
    public @ResponseBody EventInventoryResponse getInventoryForEvent(@PathVariable("eventId") Long eventId){
        return inventoryService.getEventById(eventId);
    }

    @GetMapping("/venues/{venueId}")
    public @ResponseBody VenueInventoryResponse inventoryByVenueId(@PathVariable("venueId") Long venueId){
        return inventoryService.getVenueInformation(venueId);
    }

    @PutMapping("/events/{eventId}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable("eventId") Long eventId, @PathVariable("capacity") Long ticketsBooked){
        inventoryService.updateEventCapacity(eventId, ticketsBooked);
        return ResponseEntity.ok().build();
    }
}
