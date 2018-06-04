package com.elumixor.theater.repositories;

import com.elumixor.theater.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);
}