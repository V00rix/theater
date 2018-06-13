package theater.repositories.simpleCrud;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import theater.domain.entities.Theater;
import theater.domain.exceptions.NotImplementedException;
import theater.repositories.TheaterRepository;
import theater.utility.JpaTestBase;

import java.math.BigDecimal;

public class TheaterTest extends JpaTestBase implements CRUDTest {

    //region Helpers
    private static Theater theater(int seats) {
        return new Theater("Address", "Theater Name", new BigDecimal(100), "Every Tuesday, Wednesday and Friday from 10:00 to 16:00", seats);

    }

    private static Theater theater() {
        return new Theater("Address", "Theater Name");
    }
    //endregion

    //region Autowired
    @Autowired
    TheaterRepository theaterRepository;
    //endregion

    @Before
    public void setUp() {
        theaterRepository.deleteAll();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void failOnCreateEmpty() {
        setUp();
        var theater = new Theater();
        theaterRepository.save(theater);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void failOnNoMaximumSeats() {
        setUp();
        var theater = theater();
        theaterRepository.save(theater);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void failOnSmallMaximumSeats() {
        setUp();
        var theater = theater(0);
        theaterRepository.save(theater);
    }

    @Override
    @Test
    public void create() {
        setUp();
        var theater = theater(1);
        theaterRepository.save(theater);
    }

    @Override
    @Test
    public void read() {

        throw new NotImplementedException();
    }

    @Override
    @Test
    public void update() {

        throw new NotImplementedException();
    }

    @Override
    @Test
    public void delete() {

        throw new NotImplementedException();
    }

    @Override
    @Test
    public void createAndRead() {

        throw new NotImplementedException();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createDuplicate() {
        var theater = theater(1);
        var theater2 = theater(312);
        theaterRepository.save(theater);
        theaterRepository.save(theater2);
    }
}
