package theater.repositories;

import theater.domain.entities.Performance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class PerformanceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    public void readPerformances() {
        readAndSout();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createPerformance_failOnNull() {
        var p = createAndSout();
        performanceRepository.save(p);
        readAndSout();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createPerformance_failOnNullAuthor() {
        var p = createAndSout(null, "title");
        performanceRepository.save(p);
        readAndSout();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createPerformance_failOnNullTitle() {
        var p = createAndSout("author", null);
        performanceRepository.save(p);
        readAndSout();
    }

    @Test
    public void createPerformance_pass() {
        var p = createAndSout("author", "title");
        performanceRepository.save(p);
        readAndSout();
    }

    //region Helpers
    private Performance createAndSout() {
        return createAndSout(null, null);
    }
    private Performance createAndSout(String author, String title) {
        var p = new Performance();
        p.author = author;
        p.title = title;
        System.out.println(p.id);
        return p;
    }

    private void readAndSout() {
        var performances = performanceRepository.findAll();

        performances.forEach(value -> {
            System.out.println(value.id);
        });
    }
    //endregion
}