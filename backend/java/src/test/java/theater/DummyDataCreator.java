package theater;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Repeat;
import theater.domain.entities.Hall;
import theater.domain.entities.Performance;
import theater.domain.entities.Session;
import theater.domain.entities.Theater;
import theater.repositories.*;
import theater.suites.shared.SpringTestBase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static theater.utility.Dummy.newDate;

// 'extends' for repositories as well   as annotations
public class DummyDataCreator extends SpringTestBase {
    @Autowired
    protected HallRepository hallRepository;

    @Autowired
    protected PerformanceRepository performanceRepository;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected SessionRepository sessionRepository;

    @Autowired
    protected TheaterRepository theaterRepository;

    @Test
    @Commit
    @Repeat(2)
    public void createDummyData() {
        var theater = eraseAndCreate(theaterRepository, () -> new Theater("Test address", "Test theater", new BigDecimal(100), "Always", 6));
        var hall = eraseAndCreate(hallRepository, () -> new Hall("Test hall", 13, 30));
        var performances = eraseAndCreateMultiple(performanceRepository, () -> new ArrayList<>(Arrays.asList(
                new Performance("Test author", "Test name", "test.png"),
                new Performance("Test author 2", "Test name 2"))));

        Supplier<List<Session>> sessionsSupplier = () -> {
            var sessions = new ArrayList<Session>();
            var dates = new ArrayList<>(Arrays.asList(
                    newDate(2010, 10, 3, 16, 0, 0),
                    newDate(2016, 9, 3, 16, 0, 0),
                    newDate(2016, 9, 3, 19, 30, 0),
                    newDate(2016, 9, 3, 20, 30, 0)
            ));
            performances.forEach(p -> dates.forEach(d -> sessions.add(new Session(hall, p, d))));
            return sessions;
        };
        orderRepository.deleteAll();
        clientRepository.deleteAll();
        var sessions = eraseAndCreateMultiple(sessionRepository, sessionsSupplier);
    }
}
