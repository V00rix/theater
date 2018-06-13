package theater.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import theater.domain.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    <S extends Order> List<S> findAllByConfirmed(Boolean confirmed);
}