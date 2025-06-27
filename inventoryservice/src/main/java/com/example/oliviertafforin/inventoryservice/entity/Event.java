package com.example.oliviertafforin.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "totalCapacity")
    private Long totalCapacity;

    @Column(name = "leftCapacity")
    private Long leftCapacity;

    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    @JoinColumn(name = "venue_id")
    @ManyToOne
    private Venue venue;
}
