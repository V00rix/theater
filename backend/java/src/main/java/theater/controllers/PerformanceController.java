package theater.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Performance;
import theater.repositories.PerformanceRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController extends ControllerBase {

    private final
    PerformanceRepository performanceRepository;

    @Autowired
    public PerformanceController(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    @RequestMapping(value = "/names", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, String> getAllPerformances() {
        var performances = this.performanceRepository.findAll();
        var response  = new HashMap<String, String>();

        for (var performance : performances) {
            response.put(performance.id.toString(), performance.title + " - " + performance.author);
        }

        return response;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Performance newPerformance(@RequestBody Performance performance) {
        performanceRepository.save(performance);
        return performance;
    }

    @RequestMapping(value = "/{performanceId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Performance getPerformance(@PathVariable String performanceId) {
        return performanceRepository.findById(Long.parseLong(performanceId)).orElseThrow();
    }

    @RequestMapping(value = "/{performanceId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    void updatePerformance(@RequestBody Performance performance, @PathVariable String performanceId) {
        var found = performanceRepository.findById(Long.parseLong(performanceId)).orElseThrow();

        performance.id = found.id;
        found = performance;

        performanceRepository.save(found);
    }

    @RequestMapping(value = "/{performanceId}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    void deletePerformance(@PathVariable String performanceId) {
        performanceRepository.delete(performanceRepository.findById(Long.parseLong(performanceId)).orElseThrow());
    }

}
