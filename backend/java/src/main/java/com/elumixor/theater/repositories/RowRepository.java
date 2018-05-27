package com.elumixor.theater.repositories;

import com.elumixor.theater.domain.entities.Hall;
import com.elumixor.theater.domain.entities.Row;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RowRepository extends JpaRepository<Row, Long> {

    Optional<Row> findByNumberAndHall(int number, Hall hall);
}