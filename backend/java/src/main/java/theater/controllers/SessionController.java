package theater.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theater.domain.entities.Session;
import theater.repositories.HallRepository;
import theater.repositories.PerformanceRepository;
import theater.repositories.SessionRepository;

@RestController
@RequestMapping("/api/session")
public class SessionController extends ControllerBase<Session, SessionRepository> {

    private final
    SessionRepository sessionRepository;

    private final
    HallRepository hallRepository;

    private final
    PerformanceRepository performanceRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository, HallRepository hallRepository, PerformanceRepository performanceRepository) {
        this.sessionRepository = sessionRepository;
        this.hallRepository = hallRepository;
        this.performanceRepository = performanceRepository;
    }

    @Override
    public SessionRepository repository() {
        return sessionRepository;
    }

    @Override
    public Session create(@RequestBody Session entity) {
        System.out.println(entity);
        entity.hall = hallRepository.findById(entity.hall.getId()).orElseThrow();
        entity.performance = performanceRepository.findById(entity.performance.getId()).orElseThrow();
        entity.print();
        return super.create(entity);
    }
}
