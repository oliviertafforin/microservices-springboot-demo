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
public class BookingResponse {
    private Long eventId;
    private Long ticketCount;
    private Long userId;
    private BigDecimal totalPrice;
}
