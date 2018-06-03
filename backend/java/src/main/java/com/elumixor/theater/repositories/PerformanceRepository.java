package com.elumixor.theater.repositories;

import com.elumixor.theater.domain.entities.Performance;
import com.elumixor.theater.domain.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {
}