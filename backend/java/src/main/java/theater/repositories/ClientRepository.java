package theater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theater.domain.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}