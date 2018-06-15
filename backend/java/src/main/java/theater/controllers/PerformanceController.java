package theater.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Performance;
import theater.repositories.PerformanceRepository;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController extends ControllerBase<Performance, PerformanceRepository> {

    private final
    PerformanceRepository performanceRepository;

    @Autowired
    public PerformanceController(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    @Override
    public PerformanceRepository repository() {
        return performanceRepository;
    }
}
