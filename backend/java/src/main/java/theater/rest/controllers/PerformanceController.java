package theater.rest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Performance;
import theater.repositories.PerformanceRepository;
import theater.rest.ControllerBase;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController extends ControllerBase<Performance, PerformanceRepository> {
    @Autowired
    public PerformanceController(PerformanceRepository performanceRepository) {
        super(performanceRepository);
    }
}
