package com.elumixor.theater.repositories;

import com.elumixor.theater.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Page<Session> findByPerformanceId(Long performanceId, Pageable pageable);
}