package theater.dummyData;

import org.junit.Test;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Repeat;
import theater.domain.entities.Hall;
import theater.domain.entities.Performance;
import theater.domain.entities.Session;
import theater.domain.entities.Theater;
import theater.environment.RepositoriesSmokeTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class dummyDataCreator extends RepositoriesSmokeTest {
    @Test
    @Commit
    @Repeat(2)
    public void createDummyData() {
        var theater = new Theater("Test address", "Test theater", new BigDecimal(100), "Always", 6);
        var hall = new Hall("Test hall", 13, 30);

        var performances = new ArrayList<>(Arrays.asList(
                new Performance("Test author", "Test name"),
                new Performance("Test author 2", "Test name 2")));

        var dates = new ArrayList<>(Arrays.asList(
                newDate(2010, 10, 3, 16, 0, 0),
                newDate(2016, 9, 3, 16, 0, 0),
                newDate(2016, 9, 3, 19, 30, 0),
                newDate(2016, 9, 3, 20, 30, 0)
        ));

        var sessions = new ArrayList<Session>();

        performances.forEach(p -> dates.forEach(d -> sessions.add(new Session(hall, p, d))));

        theaterRepository.deleteAll();
        theaterRepository.flush();
        theaterRepository.save(theater);
        theaterRepository.flush();

        hallRepository.deleteAll();
        hallRepository.flush();
        hallRepository.save(hall);
        hallRepository.flush();

        performanceRepository.deleteAll();
        performanceRepository.flush();
        performanceRepository.save(performances.get(0));
        performanceRepository.save(performances.get(1));
        performanceRepository.flush();

        sessionRepository.deleteAll();
        sessionRepository.flush();
        sessionRepository.saveAll(sessions);
        sessionRepository.flush();
    }

    @SuppressWarnings("SameParameterValue")
    private static Timestamp newDate(int y, int m, int d, int h, int mm, int ss) {
        var cal = Calendar.getInstance();
        cal.set(y, m, d, h, mm, ss);
        return new Timestamp(cal.getTime().getTime());
    }
}
