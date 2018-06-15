package theater.controllers;

import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Hall;
import theater.domain.exceptions.BaseHttpException;
import theater.repositories.HallRepository;

@RestController
@RequestMapping("/api/hall")
public class HallController extends ControllerBase<Hall, HallRepository> {
    private final
    HallRepository hallRepository;

    public HallController(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public HallRepository repository() {
        return hallRepository;
    }

    @Override
    public Hall create(@RequestBody Hall hall) {
        return createActualNew(hall.name);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Hall createActualNew(@RequestBody String name) {
        if (name == null || name.isEmpty()) {
            throw new BaseHttpException("Hall name was not present in request.");
        }
        var hall = new Hall(name);
        hallRepository.save(hall);
        return hall;
    }
}
