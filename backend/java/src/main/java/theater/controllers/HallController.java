package theater.controllers;


import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Hall;
import theater.domain.exceptions.BaseHttpException;
import theater.repositories.HallRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hall")
public class HallController extends ControllerBase {
    // FIXME: 14-Jun-18 Mocking the behaviour of hall creation for now
    private final
    HallRepository hallRepository;

    public HallController(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @RequestMapping(value = "/names", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<Integer, String> getNames() {
        var results = hallRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
        var response = new HashMap<Integer, String>();

        results.forEach(r -> response.put(r.id.intValue(), r.name));

        return response;
    }

    @RequestMapping(value = "/{hallId}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Hall getHall(@PathVariable String hallId) {
        return hallRepository.findById(Long.parseLong(hallId)).orElseThrow();
    }

    // TODO: 14-Jun-18 Change this after FE implementation
    // i.e. most likely won't be receiving Hall object
    @RequestMapping(value = "/{hallId}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    void setHall(@RequestBody Hall hallData, @PathVariable String hallId) {
        var hall = hallRepository.findById(Long.parseLong(hallId)).orElseThrow();

        hallData.id = hall.id;
        hall = hallData;

        hallRepository.save(hall);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Hall createNewHall(@RequestBody String name) {
        if (name == null || name.isEmpty()) {
            throw new BaseHttpException("Hall name was not present in request.");
        }
        var hall = new Hall(name);
        hallRepository.save(hall);
        return hall;
    }

    @RequestMapping(value = "/{hallId}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    void deleteHall(@PathVariable String hallId) {
        hallRepository.delete(hallRepository.findById(Long.parseLong(hallId)).orElseThrow());
    }
}
