package theater.repositories.simpleCrud;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import theater.domain.entities.Hall;
import theater.domain.exceptions.NotImplementedException;
import theater.repositories.CRUDTests;
import theater.repositories.HallRepository;
import theater.repositories.SeatRepository;
import theater.utility.Dummy;
import theater.utility.EntityTestBase;
import theater.utility.JpaTestBase;

import static org.junit.Assert.assertEquals;

public class HallTest extends JpaTestBase implements CRUDTests {
    @Autowired
    HallRepository hallRepository;
    @Autowired
    SeatRepository seatRepository;

    @Before
    public void setUp() {
        hallRepository.deleteAll();
    }

    @Override
    @Test
    public void create() {
        setUp();
        var hall = Dummy.hall();
        hallRepository.save(hall);
    }

    @Test
    public void createEmpty() {
        setUp();
        var hall = new Hall("Name");
        hallRepository.save(hall);
    }

    @Override
    @Test
    public void read() {
        EntityTestBase.findAllAndPrint(hallRepository);
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
        setUp();
        var height = 5;
        var width = 5;

        var hall = new Hall("Name", height, width);
        hallRepository.save(hall);

        var halls = EntityTestBase.findAllAndPrint(hallRepository);

        var seats = seatRepository.findAll();

        assertEquals(height * width, seats.size());

        assertEquals(1, halls.size());
        assert "Name".equals(halls.get(0).name);

        var totalSeats = 0;
        for (Hall h : halls) {
            totalSeats += h.seats.size();
        }
        assertEquals(height * width, totalSeats);
    }
}
