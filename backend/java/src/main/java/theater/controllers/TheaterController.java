package theater.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import theater.domain.entities.Theater;
import theater.repositories.TheaterRepository;

@RestController
@RequestMapping("/api/theater")
public class TheaterController extends ControllerBase {

    private final
    TheaterRepository theaterRepository;

    @Autowired
    public TheaterController(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Theater getTheaterData() {
        var theaters = theaterRepository.findAll();
        if (theaters.size() < 1) {
            System.out.println("Could not find theater data.");
            return null;
        } else {
            var theater = theaters.get(0);
            theater.print();
            return theater;
        }
    }

//    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody
//    void createTheaterData(@RequestBody Theater theaterData) {
//        theaterRepository.save(theaterData);
//    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    void updateTheaterData(@RequestBody Theater theaterData) {
        var theaters = theaterRepository.findAll();
        if (theaters.size() < 1) {
//            createTheaterData(theaterData);
        } else {
            var theater = theaters.get(0);
            theater .copy(theaterData);
            theaterRepository.save(theater);
        }
    }

    @Override
    public JpaRepository repository() {
        return theaterRepository;
    }
}
