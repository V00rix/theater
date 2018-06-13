package theater.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hall")
public class HallController extends ControllerBase {

    //    private final
    //    PerformanceRepository performanceRepository;
    //
    //    @Autowired
    //    public HallController(PerformanceRepository performanceRepository, OrderRepository orderRepository) {
    //        this.performanceRepository = performanceRepository;
    //    }
    //
    //    @GetMapping("/performances")
    //    public PerformanceResponse getAllPerformances() {
    //        return new PerformanceResponse(this.performanceRepository.findAll());
    //    }
    //
    //    @GetMapping("/sessions")
    //    public SessionResponse getAllSessions() {
    ////        return new SessionResponse(new ArrayList<Session>(this.performanceRepository.findAll().stream().map(x -> x.sessions).reduce((x, y) -> {
    //////            x.addAll(y);
    ////            return x;
    ////        }).orElse(new HashSet<>())));
    //        return null;
    //    }
    @RequestMapping("/")
    public @ResponseBody
    String greeting() {
        return "Hello World";
    }
}
