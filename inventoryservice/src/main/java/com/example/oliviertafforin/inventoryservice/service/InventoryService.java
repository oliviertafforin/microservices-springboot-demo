package com.example.oliviertafforin.inventoryservice.service;

import com.example.oliviertafforin.inventoryservice.dto.EventInventoryResponse;
import com.example.oliviertafforin.inventoryservice.dto.VenueInventoryResponse;
import com.example.oliviertafforin.inventoryservice.entity.Event;
import com.example.oliviertafforin.inventoryservice.entity.Venue;
import com.example.oliviertafforin.inventoryservice.repository.EventRepository;
import com.example.oliviertafforin.inventoryservice.repository.VenueRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    public InventoryService(EventRepository eventRepository, VenueRepository venueRepository) {
        this.eventRepository = eventRepository;
        this.venueRepository = venueRepository;
    }

    public List<EventInventoryResponse> getAllEvents() {
        final List<Event> events = eventRepository.findAll();

        return events.stream().map(event ->
                        EventInventoryResponse.builder()
                                .venue(event.getVenue())
                                .capacity(event.getLeftCapacity())
                                .event(event.getName()).build())
                .collect(Collectors.toList());
    }

    public VenueInventoryResponse getVenueInformation(Long venueId) {
        final Venue venue = venueRepository.findById(venueId).orElse(null);
        return VenueInventoryResponse.builder()
                .venueId(venue.getId())
                .totalCapacity(venue.getTotalCapacity())
                .venueName(venue.getName())
                .build();
    }

    public EventInventoryResponse getEventById(Long eventId) {
        final Event event = eventRepository.findById(eventId).orElse(null);
        return EventInventoryResponse.builder()
                .event(event.getName())
                .venue(event.getVenue())
                .eventId(eventId)
                .ticketPrice(event.getTicketPrice())
                .capacity(event.getLeftCapacity())
                .build();
    }

    public void updateEventCapacity(Long eventId, Long ticketsBooked) {
        final Event event = eventRepository.findById(eventId).orElse(null);
        event.setLeftCapacity(event.getLeftCapacity() - ticketsBooked);
        eventRepository.saveAndFlush(event);
        log.info("Capacity updated for event {} with {} tickets booked", eventId, ticketsBooked);
    }
}
