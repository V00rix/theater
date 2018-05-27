package com.elumixor.theater.repositories;

import com.elumixor.theater.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}