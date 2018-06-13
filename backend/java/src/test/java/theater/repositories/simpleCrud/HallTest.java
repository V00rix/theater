package theater.repositories.simpleCrud;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import theater.common.JpaTestBase;
import theater.domain.entities.Hall;
import theater.repositories.HallRepository;

import static org.junit.Assert.assertEquals;

public class HallTest extends JpaTestBase {
    @Autowired
    HallRepository hallRepository;

    // TODO: 13-Jun-18 delete later -> use TestNG
    @Before
    @Commit
    public void deleteAll() {
        hallRepository.deleteAll();
    }

    @Test
    public void getHalls() {
        var halls = hallRepository.findAll();
        var size = halls.size();

        if (size == 0) {
            System.out.println("No halls created yet.");
        } else {
            System.out.println("Retrieved " + size + " hall(-s).");
            halls.forEach(hall -> {
                System.out.println("Hall " + hall.id);
                System.out.println("Hall contains " + hall.seats.size() + " seats.");
                hall.seats.forEach(System.out::println);
            });
        }
    }

    @Test
    public void createEmptyHall() {
        hallRepository.deleteAll();
        var hall = new Hall("Name");
        hallRepository.save(hall);
    }

    @Test
    public void createSimpleHall() {
        hallRepository.deleteAll();
        var hall = new Hall("Name", 10, 10);
        hallRepository.save(hall);
    }

    @Test
    @Commit
    public void createAndRead() {
        var height = 5;
        var width = 5;

        hallRepository.deleteAll();
        var hall = new Hall("Name", height, width);
        hallRepository.save(hall);

        var halls = hallRepository.findAll();
        assertEquals(1, halls.size());

        var totalSeats = 0;
        for (Hall h : halls) {
            totalSeats += h.seats.size();
            h.seats.forEach(System.out::println);
        }
        assertEquals(height * width, totalSeats);
    }
}
