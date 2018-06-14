package theater.controllers;

import javafx.util.Pair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import theater.domain.entities.Hall;
import theater.repositories.HallRepository;
import theater.utility.Dummy;
import theater.utility.RESTTestBase;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HallControllerTest extends RESTTestBase implements RESTTest {
    private static final String url = "/api/hall/";

    @Autowired
    HallRepository hallRepository;

    @Override
    @Test
    public void getTest() throws Exception {
        hallRepository.deleteAll();
        var hallNames = new String[]{"hall one", "hall two", "hall name three"};
        var map = new ArrayList<Pair<Integer, String>>();
        for (var name : hallNames) {
            var entity = new Hall(name);
            hallRepository.save(entity);
            map.add(new Pair<>(entity.getId().intValue(), entity.name));
        }

        var res = mockMvc.perform(get(url + "names")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        for (var p : map) {
            res.andExpect(jsonPath("$." + p.getKey(), is(p.getValue())));
        }
    }

    // FIXME: 14-Jun-18 Failing because of bad JPA 'seat -> hall' relation (fix jpa mappings!)
    @Test
//    @Ignore
    public void getSpecificHallTest() throws Exception {
        hallRepository.deleteAll();
        var halls = new Hall[]{
                new Hall("hall one", 3, 5),
//                new Hall("hall two", 10, 10),
//                new Hall("hall name three", 5, 30),
//                Dummy.hall()
        };

        for (var hall : halls) {
            hallRepository.save(hall);
        }

        for (var hall : halls) {
            var res = mockMvc.perform(get(url + hall.getId().toString())).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(content().json(mapper.writeValueAsString(hall)));
        }
    }

    @Override
    @Test
    public void postTest() throws Exception {
        hallRepository.deleteAll();

        var hallName = "test new hall name";

        // Perform request
        mockMvc.perform(post(url + "new")
                .content(hallName)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        var halls = hallRepository.findAll();
        assert !halls.isEmpty();
        assert halls.size() == 1;
        var hall = halls.get(0);
        assert hall.name.equals(hallName);
    }

    @Test
    public void updateTest() throws Exception {
        hallRepository.deleteAll();
        var entityFirst = Dummy.hall();

        // Save
        hallRepository.save(entityFirst);

        // Change
        var entitySecond = Dummy.hall();
        entitySecond.name = "Hall TWO";

        var id = hallRepository.findAll().get(0).getId();

        // Perform request
        mockMvc.perform(post(url + id.toString())
                .content(mapper.writeValueAsString(entitySecond))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Check
        var created = hallRepository.findAll().get(0);

        assert created != null;
        assert !created.equals(entityFirst);
        assert created.equals(entitySecond);
    }

    @Test
    public void deleteTest() throws Exception {
        hallRepository.deleteAll();

        var hall = Dummy.hall();
        hallRepository.save(hall);
        var id = hall.getId();

        mockMvc.perform(delete(url + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        var halls = hallRepository.findAll();

        assert halls.isEmpty();
    }

    @Test
    public void deleteSpecificTest() throws Exception {
        hallRepository.deleteAll();

        hallRepository.save(Dummy.hall());
        var hall = new Hall("Second Hall");
        hallRepository.save(hall);
        var id = hall.getId();

        mockMvc.perform(delete(url + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        var halls = hallRepository.findAll();

        assert !halls.isEmpty();
        assert halls.size() == 1;
        assert !hallRepository.findById(id).isPresent();
    }
}