package com.example.oliviertafforin.bookingservice.service;

import com.example.oliviertafforin.bookingservice.client.InventoryServiceClient;
import com.example.oliviertafforin.bookingservice.dto.BookingRequest;
import com.example.oliviertafforin.bookingservice.dto.BookingResponse;
import com.example.oliviertafforin.bookingservice.dto.InventoryResponse;
import com.example.oliviertafforin.bookingservice.entity.Customer;
import com.example.oliviertafforin.bookingservice.event.BookingEvent;
import com.example.oliviertafforin.bookingservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public BookingService(CustomerRepository customerRepository, InventoryServiceClient inventoryServiceClient, KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    public BookingResponse createBooking(BookingRequest bookingRequest) {

        final Customer customer = customerRepository.findById(bookingRequest.getUserId()).orElse(null);
        if (customer == null) {
            throw new RuntimeException("User not found");
        }

        final InventoryResponse response = inventoryServiceClient.getInventory(bookingRequest.getEventId());

        if (response.getCapacity() < bookingRequest.getTicketCount()) {
            throw new RuntimeException("Not enough inventory");
        }

        final BookingEvent bookingEvent = createBookingEvent(bookingRequest, customer, response);

        kafkaTemplate.send("booking", bookingEvent);
        log.info("Booking sent to Kafka : {}", bookingEvent);

        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

    private BookingEvent createBookingEvent(BookingRequest bookingRequest, Customer customer, InventoryResponse response) {
        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(bookingRequest.getEventId())
                .ticketCount(bookingRequest.getTicketCount())
                .totalPrice(response.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())))
                .build();
    }
}
