package com.example.oliviertafforin.bookingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private String event;
    private Long capacity;
    private VenueResponse venue;
    private BigDecimal ticketPrice;
    private Long eventId;
}
