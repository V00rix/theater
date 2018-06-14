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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        for (var name : hallNames) {
            var entity = new Hall(name);
            hallRepository.save(entity);
        }

        var halls = hallRepository.findAll();
        var map = new ArrayList<Pair<Integer, String>>();
        halls.forEach(h -> {
            map.add(new Pair<>(h.id.intValue(), h.name));
        });

        var res = mockMvc.perform(get(url + "names")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        for (var p : map) {
            res.andExpect(jsonPath("$." + p.getKey(), is(p.getValue())));
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

        var id = hallRepository.findAll().get(0).id;

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
}