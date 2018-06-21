package theater.rest.controllers;

import org.springframework.web.bind.annotation.*;
import theater.domain.Seat;
import theater.domain.entities.Hall;
import theater.domain.exceptions.BaseHttpException;
import theater.repositories.HallRepository;
import theater.rest.ControllerBase;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hall")
public class HallController extends ControllerBase<Hall, HallRepository> {
    public HallController(HallRepository hallRepository) {
        super(hallRepository);
    }

    @Override
    public Hall saveOrUpdate(@RequestBody Hall hall) {
        return createActualNew(hall.name);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Hall createActualNew(@RequestBody String name) {
        if (name == null || name.isEmpty()) {
            throw new BaseHttpException("Hall name was not present in request.");
        }
        var hall = new Hall(name);
        repository.save(hall);
        return hall;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Map<String, Hall> getAll() {
        var entities = repository.findAll();
        var response = new HashMap<String, Hall>();

        for (var entity : entities) {
            response.put(entity.getId().toString(), entity);
        }
        return response;
    }
}
