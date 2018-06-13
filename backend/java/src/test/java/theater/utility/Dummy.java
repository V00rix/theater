package theater.utility;

import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.*;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * Helper class for dummy entities
 */
public abstract class Dummy {

    public static Theater theater() {
        return new Theater("Country", "City", "Street", "House", "Name", 5);
    }

    public static Hall hall() {
        return new Hall("Hall Name", 10, 10);
    }

    public static Performance performance() {
        return new Performance("Author", "Title");
    }

    public static Session session(Hall hall, Performance performance) {
        return new Session(hall, performance, new Timestamp(System.currentTimeMillis()));
    }

    public static Order order(Session session, Client client) {
        return new Order(session, client, Order.Checkout.PAY_BEFORE);
    }

    public static <R, T extends JpaRepository<R, Long>> Optional<R> find(T repository) {
        var results = repository.findAll();
        if (results.size() > 0) {
            return Optional.of(results.get(0));
        } else {
            return Optional.empty();
        }
    }

    public static Client client() {
        return new Client("emxail@example.com", "Client Dummy Name");
    }
}
