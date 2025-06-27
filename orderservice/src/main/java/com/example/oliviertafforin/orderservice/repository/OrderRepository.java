package com.example.oliviertafforin.orderservice.repository;

import com.example.oliviertafforin.orderservice.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {
}
