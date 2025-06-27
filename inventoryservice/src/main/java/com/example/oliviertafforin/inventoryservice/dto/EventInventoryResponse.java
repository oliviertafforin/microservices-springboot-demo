package com.example.oliviertafforin.inventoryservice.dto;

import com.example.oliviertafforin.inventoryservice.entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInventoryResponse {
    private String event;
    private Long capacity;
    private Venue venue;
    private BigDecimal ticketPrice;
    private Long eventId;
}
