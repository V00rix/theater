package theater.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import theater.domain.entities.EntityBase;

import java.util.function.Supplier;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public interface RESTTest {

    ObjectMapper mapper = new ObjectMapper();

    void getTest() throws Exception;

    void postTest() throws Exception;

    /**
     * Logic for basic get testing
     */
    static <R extends EntityBase, T extends JpaRepository<R, Long>>
    void defaultGetTest(T repository, Supplier<R> supplier, MockMvc mvc, String url) throws Exception {

        // Create entity
        repository.deleteAll();
        var entity = supplier.get();
        repository.save(entity);

        // Perform request
        mvc.perform(get(url)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(entity)));
    }

    /**
     * Logic for basic post testing
     */
    static <R extends EntityBase, T extends JpaRepository<R, Long>>
    void defaultPostTest(T repository, Supplier<R> supplier, MockMvc mvc, String url) throws Exception {

        // Create entity
        repository.deleteAll();
        var entity = supplier.get();

        // Perform request
        mvc.perform(post(url)
                .content(mapper.writeValueAsString(entity))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


        // Check
        var created = repository.findAll().get(0);
        assert created != null;
        assert created.equalz(entity);
    }

    /**
     * Logic for basic update testing
     */
    static <R extends EntityBase, T extends JpaRepository<R, Long>>
    void defaultUpdateTest(T repository, Supplier<R> first, Supplier<R> second, MockMvc mvc, String url) throws Exception {

        // Create entity
        repository.deleteAll();
        var entityFirst = first.get();

        // Save
        repository.save(entityFirst);

        // Change
        var entitySecond = second.get();

        // Perform request
        mvc.perform(post(url)
                .content(mapper.writeValueAsString(entitySecond))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Check
        var created = repository.findAll().get(0);

        assert created != null;
        assert !created.equalz(entityFirst);
        assert created.equalz(entitySecond);
    }
}
