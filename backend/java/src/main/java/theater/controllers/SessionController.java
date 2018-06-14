package theater.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Session;
import theater.repositories.HallRepository;
import theater.repositories.PerformanceRepository;
import theater.repositories.SessionRepository;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    @Transactional
    Map<String, Session> getAllSessions() throws JsonProcessingException {
        var sessions = this.sessionRepository.findAll();
        var response = new HashMap<String, Session>();

        for (var session : sessions) {
            response.put(session.getId().toString(), session);
        }
        return response;
    }

//    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody
//    Session newPerformance(@RequestBody Session session) {
//        sessionRepository.save(session);
//        return session;
//    }


    @Override
    public Session createNew(@RequestBody Session entity) {
        System.out.println(entity);
        entity.hall = hallRepository.findById(entity.hall.getId()).orElseThrow();
        entity.performance = performanceRepository.findById(entity.performance.getId()).orElseThrow();
        entity.print();
        return super.createNew(entity);
//        return null;
    }

    @Override
    public SessionRepository repository() {
        return sessionRepository;
    }
    //
    //    @RequestMapping(value = "/{performanceId}", method = RequestMethod.GET, produces = "application/json")
    //    public @ResponseBody
    //    Performance getPerformance(@PathVariable String performanceId) {
    //        return performanceRepository.findById(Long.parseLong(performanceId)).orElseThrow();
    //    }
    //
    //    @RequestMapping(value = "/{performanceId}", method = RequestMethod.POST, produces = "application/json")
    //    public @ResponseBody
    //    void updatePerformance(@RequestBody Performance performance, @PathVariable String performanceId) {
    //        var found = performanceRepository.findById(Long.parseLong(performanceId)).orElseThrow();
    //
    //        performance.id = found.id;
    //        found = performance;
    //
    //        performanceRepository.save(found);
    //    }
    //
    //    @RequestMapping(value = "/{performanceId}", method = RequestMethod.DELETE, produces = "application/json")
    //    public @ResponseBody
    //    void deletePerformance(@PathVariable String performanceId) {
    //        performanceRepository.delete(performanceRepository.findById(Long.parseLong(performanceId)).orElseThrow());
    //    }

}
