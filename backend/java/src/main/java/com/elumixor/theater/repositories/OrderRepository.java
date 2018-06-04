package com.elumixor.theater.repositories;

import com.elumixor.theater.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    <S extends Order> List<S> findAllByConfirmed(Boolean confirmed);
}