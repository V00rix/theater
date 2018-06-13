package theater.repositories.simpleCrud;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import theater.common.JpaTestBase;
import theater.domain.entities.Theater;
import theater.repositories.TheaterRepository;

public class TheaterTest extends JpaTestBase {
    public static Theater theater(int seats) {
        return new Theater("Country", "City", "Street", "House", "Name", seats);
    }
    public static Theater theater() {
        return new Theater("Country", "City", "Street", "House", "Name");
    }

    @Autowired
    TheaterRepository theaterRepository;

    @Test(expected = DataIntegrityViolationException.class)
    public void failOnCreateEmpty() {
        var theater = new Theater();
        theaterRepository.save(theater);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void failOnNoMaximumSeats() {
        var theater = theater();
        theaterRepository.save(theater);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void failOnSmallMaximumSeats() {
        var theater = theater(0);
        theaterRepository.save(theater);
    }

    @Test
    public void createValid() {
        var theater = theater(1);
        theaterRepository.save(theater);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void failOnDuplicate() {
        var theater = theater(1);
        var theater2 = theater(312);
        theaterRepository.save(theater);
        theaterRepository.save(theater2);
    }
}
