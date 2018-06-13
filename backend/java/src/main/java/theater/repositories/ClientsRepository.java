package theater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theater.domain.entities.Client;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);
}