package theater.repositories.simpleCrud;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import theater.domain.entities.Hall;
import theater.domain.entities.Performance;
import theater.domain.exceptions.NotImplementedException;
import theater.repositories.HallRepository;
import theater.repositories.PerformanceRepository;
import theater.repositories.SessionRepository;
import theater.utility.Dummy;
import theater.utility.EntityTestBase;
import theater.utility.JpaTestBase;

public class SessionTest extends JpaTestBase implements CRUDTest {

    //region Autowired
    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    SessionRepository sessionRepository;
    //endregion

    //region Fields
    private Performance performance;
    private Hall hall;
    //endregion

    @Before
    public void setUp() throws Exception {
        performance = Dummy.find(performanceRepository).orElse(Dummy.performance());
        performanceRepository.save(performance);
        hall = Dummy.find(hallRepository).orElse(Dummy.hall());
        System.out.println("BEFORE SAVE");
        hallRepository.save(hall);
        System.out.println("AFTER SAVE");
        sessionRepository.deleteAll();
    }

    @Override
    @Test
    public void create() {
        sessionRepository.deleteAll();
        var session = Dummy.session(hall, performance);
        hall.print();
        sessionRepository.save(session);
    }

    @Override
    @Test
    public void read() {
        EntityTestBase.findAllAndPrint(sessionRepository);
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
        sessionRepository.deleteAll();
        var session = Dummy.session(hall, performance);
        sessionRepository.save(session);
        var found = EntityTestBase.findAllAndPrint(sessionRepository);
        assert session.equals(found.get(0));
    }

}
