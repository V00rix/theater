package com.elumixor.theater.repositories;

import com.elumixor.theater.domain.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}