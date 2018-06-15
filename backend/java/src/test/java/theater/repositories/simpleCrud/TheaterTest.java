package theater.repositories.simpleCrud;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import theater.domain.entities.Theater;
import theater.domain.exceptions.NotImplementedException;
import theater.repositories.TheaterRepository;
import theater.utility.JpaTestBase;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

public class TheaterTest extends JpaTestBase implements CRUDTest {

    @Autowired
    TheaterRepository theaterRepository;

    @Test(expected = ConstraintViolationException.class)
    public void failOnCreateEmpty() {
        setUp();
        var theater = new Theater();
        theaterRepository.saveAndFlush(theater);
    }

    @Before
    public void setUp() {
        theaterRepository.deleteAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void failOnNoMaximumSeats() {
        setUp();
        var theater = theater();
        System.out.println(theater.maximumSeats);
        theaterRepository.saveAndFlush(theater);
    }

    private static Theater theater() {
        return new Theater("Address", "Theater Name");
    }

    @Test(expected = ConstraintViolationException.class)
    public void failOnSmallMaximumSeats() {
        setUp();
        var theater = theater(0);
        theaterRepository.saveAndFlush(theater);
    }

    private static Theater theater(int seats) {
        return new Theater("Address", "Theater Name", new BigDecimal(100), "Every Tuesday, Wednesday and Friday from 10:00 to 16:00", seats);
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
        theaterRepository.saveAndFlush(theater2);
    }
}
