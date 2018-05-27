package com.elumixor.theater.repositories;

import com.elumixor.theater.domain.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HallRepository extends JpaRepository<Hall, Long> {

    Optional<Hall> findById(Long id);
}