package theater.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import theater.domain.entities.Session;
import theater.repositories.SessionRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/session")
public class SessionController extends ControllerBase {

    private final
    SessionRepository sessionRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    @Transactional
    Map<String, Session> getAllSessions() throws JsonProcessingException {
        var sessions = this.sessionRepository.findAll();
        var response = new HashMap<String, Session>();

        //        var mapper = new ObjectMapper();

        for (var session : sessions) {
//            session.print();
//            System.out.println(session.hall.getId());
//            System.out.println(session.performance.getId());
            //            System.out.println(mapper.writeValueAsString(session));
            response.put(session.getId().toString(), session);
        }


        //        var s = new Session(new Hall("hall name", 3, 3), new Performance("authoer", "title"),
        //                new Timestamp(System.currentTimeMillis()));
        //
        //        s.print();

        return response;
    }
    //
    //    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    //    public @ResponseBody
    //    Performance newPerformance(@RequestBody Performance performance) {
    //        performanceRepository.save(performance);
    //        return performance;
    //    }
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
