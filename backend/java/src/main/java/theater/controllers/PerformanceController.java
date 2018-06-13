package theater.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theater.domain.http.PerformanceResponse;
import theater.domain.http.SessionResponse;

@RestController
@RequestMapping("/api")
public class PerformanceController extends ControllerBase {
//
//    private final
//    PerformanceRepository performanceRepository;
//
//    @Autowired
//    public PerformanceController(PerformanceRepository performanceRepository, OrderRepository orderRepository) {
//        this.performanceRepository = performanceRepository;
//    }

    @GetMapping("/performances")
    public PerformanceResponse getAllPerformances() {
//        return new PerformanceResponse(this.performanceRepository.findAll());
        return null;
    }

    @GetMapping("/sessions")
    public SessionResponse getAllSessions() {
//        return new SessionResponse(new ArrayList<Session>(this.performanceRepository.findAll().stream().map(x -> x.sessions).reduce((x, y) -> {
////            x.addAll(y);
//            return x;
//        }).orElse(new HashSet<>())));
        return null;
    }


}
