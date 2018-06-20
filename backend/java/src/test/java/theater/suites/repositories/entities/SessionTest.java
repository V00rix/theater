package theater.suites.repositories.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Session;
import theater.repositories.HallRepository;
import theater.repositories.PerformanceRepository;
import theater.repositories.SessionRepository;
import theater.suites.repositories.EntityTestBase;
import theater.utility.Dummy;

import java.util.List;

public class SessionTest extends EntityTestBase<Session> {

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    SessionRepository repository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Override
    protected JpaRepository<Session, Long> getRepository() {
        return repository;
    }

    @Override
    protected Session construct() {
        var performance = eraseAndCreate(performanceRepository, Dummy::performance);
        var hall = eraseAndCreate(hallRepository, Dummy::hall);
        return Dummy.session(hall, performance);
    }

    @Override
    protected List<Session> constructMultiple() {
        var performances = eraseAndCreateMultiple(performanceRepository, Dummy::performances);
        var halls = eraseAndCreateMultiple(hallRepository, Dummy::halls);
        return Dummy.sessions(halls, performances);
    }
}
