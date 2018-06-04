package com.elumixor.theater.repositories;

import com.elumixor.theater.domain.entities.Hall;
import com.elumixor.theater.domain.entities.Row;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RowRepository extends JpaRepository<Row, Long> {

    Optional<Row> findByNumberAndHall(int number, Hall hall);
}