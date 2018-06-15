package theater.controllers.generic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import theater.domain.entities.EntityBase;
import theater.utility.RESTTestBase;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class GenericControllerTest<E extends EntityBase, R extends JpaRepository<E, Long>> extends RESTTestBase {
    //    private static final String url = "/api/hall/";

    //    @Autowired
    //    HallRepository hallRepository;
    //
    //    @Test
    //    //    @Ignore
    //    public void getSpecificHallTest() throws Exception {
    //        hallRepository.deleteAll();
    //        var halls = new Hall[] {
    //                new Hall("hall one", 3, 5),
    //                //                new Hall("hall two", 10, 10),
    //                //                new Hall("hall name three", 5, 30),
    //                //                Dummy.hall()
    //        };
    //
    //        for (var hall : halls) {
    //            hallRepository.save(hall);
    //        }
    //
    //        for (var hall : halls) {
    //            var res = mockMvc.perform(get(url + hall.getId().toString())).andDo(print())
    //                    .andExpect(status().isOk())
    //                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    //                    .andExpect(content().json(mapper.writeValueAsString(hall)));
    //        }
    //    }
    //
    //    @Test
    //    public void updateTest() throws Exception {
    //        hallRepository.deleteAll();
    //        var entityFirst = Dummy.hall();
    //
    //        // Save
    //        hallRepository.save(entityFirst);
    //
    //        // Change
    //        var entitySecond = Dummy.hall();
    //        entitySecond.name = "Hall TWO";
    //
    //        var id = hallRepository.findAll().get(0).getId();
    //
    //        // Perform request
    //        mockMvc.perform(post(url + id.toString())
    //                .content(mapper.writeValueAsString(entitySecond))
    //                .contentType(MediaType.APPLICATION_JSON))
    //                .andDo(print())
    //                .andExpect(status().isOk());
    //
    //        // Check
    //        var created = hallRepository.findAll().get(0);
    //
    //        assert created != null;
    //        assert !created.equalz(entityFirst);
    //        assert created.equalz(entitySecond);
    //    }
    //
    //    @Test
    //    public void deleteTest() throws Exception {
    //        hallRepository.deleteAll();
    //
    //        var hall = Dummy.hall();
    //        hallRepository.save(hall);
    //        var id = hall.getId();
    //
    //        mockMvc.perform(delete(url + id.toString())
    //                .contentType(MediaType.APPLICATION_JSON))
    //                .andDo(print())
    //                .andExpect(status().isOk());
    //
    //        var halls = hallRepository.findAll();
    //
    //        assert halls.isEmpty();
    //    }
    //
    //    @Test
    //    public void deleteSpecificTest() throws Exception {
    //        hallRepository.deleteAll();
    //
    //        hallRepository.save(Dummy.hall());
    //        var hall = new Hall("Second Hall");
    //        hallRepository.save(hall);
    //        var id = hall.getId();
    //
    //        mockMvc.perform(delete(url + id.toString())
    //                .contentType(MediaType.APPLICATION_JSON))
    //                .andDo(print())
    //                .andExpect(status().isOk());
    //
    //        var halls = hallRepository.findAll();
    //
    //        assert !halls.isEmpty();
    //        assert halls.size() == 1;
    //        assert !hallRepository.findById(id).isPresent();
    //    }
    //

    protected abstract String url();

    protected abstract R repository();

    protected abstract E entity();

    protected abstract E[] multiple();

    private ResultActions[] checks;
//
//    protected ResultActions[] buildChecks() {
//        return
//    }

    @Before
    protected void setUp() {
//        checks = buildChecks();
    }

    @After
    protected void tearDown() {
    }

    protected void beforeEach() {
        repository().deleteAll();
    }

    @Test
    public void repositoryPresentTest() {
        assertThat(repository()).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        beforeEach();
        var multiple = multiple();
        for (var entity : multiple) {
            repository().save(entity);
        }

        var res = mockMvc.perform(get(url())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getFirst() {
        beforeEach();
    }

    @Test
    public void updateFirst() {
        beforeEach();
    }

    @Test
    public void getNames() {
        beforeEach();
    }

    @Test
    public void create() {
        beforeEach();
    }

    @Test
    public void delete() {
        beforeEach();
    }

    @Test
    public void getSpecific() {
        beforeEach();
    }

    @Test
    public void update() {
        beforeEach();
    }
}