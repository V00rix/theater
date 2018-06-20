package theater.suites.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import theater.domain.entities.EntityBase;
import theater.suites.shared.GenericTestBase;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.fail;

@SuppressWarnings("unchecked")
public abstract class ControllerTestBase<E extends EntityBase> extends GenericTestBase<E> {
    protected ObjectMapper mapper = new ObjectMapper();

    private String url;

    @Override
    public void setUp() {
        super.setUp();
        this.url = getUrl();
        assert url != null;
    }

    @Test
    public void getAll() {
        deleteAndFlush();
        var multiple = createAndSaveMultiple();
        repository.flush();

        var expected = new HashMap<String, E>();

        for (var entity : multiple) {
            expected.put(entity.getId().toString(), entity);
        }

        try {
            get(url, expected)
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            failMvc(e);
        }
    }

    @Test
    public void getFirst() {
        deleteAndFlush();
        var multiple = createAndSaveMultiple();
        var expected = multiple.get(0);

        try {
            get(url + "/first", expected)
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            failMvc(e);
        }
    }

    @Test
    public void createOrUpdateFirst() {
        deleteAndFlush();

        var created = construct();
        var res = post(url, created);

        var found = findExactCount(1).get(0);
        try {
            res.andExpect(content().string(found.id.toString()));
            assert found.equalz(created);

            var second = constructMultiple().get(0);
            res = post(url, second);

            found = findExactCount(1).get(0);
            res.andExpect(content().string(found.id.toString()));

            assert found.equalz(second);
            assert !found.equalz(created);
        } catch (Exception e) {
            failMvc(e);
        }
    }

    @Test
    public void getNames() {
        deleteAndFlush();

        var multiple = createAndSaveMultiple();
        var expected = new HashMap<Long, String>();

        multiple.forEach(m -> expected.put(m.getId(), m.stringValue()));

        try {
            get(url + "/names", expected)
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            failMvc(e);
        }
    }

    @Test
    public void create() {
        deleteAndFlush();

        var created = constructMultiple();

        List<E> found = null;
        for (var entity : created) {
            post(url + "/new", entity);
            found = findAll();
            assert contains(found, entity);
        }

        assert found != null;
        assert found.size() == constructMultiple().size();
    }

    @Test
    public void delete() {
        deleteAndFlush();
        var entity = createAndSave();
        repository.flush();

        post(url + "/delete/" + entity.getId(), null);

        var found = findAll();
        assert found.isEmpty();

        deleteAndFlush();
        var multiple = createAndSaveMultiple();
        repository.flush();

        for (int i = 0; i < multiple.size(); i++) {
            E m = multiple.get(i);
            post(url + "/delete/" + m.getId(), null);
            found = findAll();
            assert !found.contains(m);
            assert i >= multiple.size() - 1 || !found.isEmpty();
        }
    }

    @Test(expected = NestedServletException.class)
    public void getSpecific() throws Exception {
        deleteAndFlush();

        var multiple = createAndSaveMultiple();

        for (var m : multiple) {
            get(url + "/id/" + m.getId(), m);
        }

        mockMvc.perform(MockMvcRequestBuilders.get(url + "/id/"
                + max(multiple.stream().map(EntityBase::getId).collect(Collectors.toList())) + 1)).andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void update() {
        deleteAndFlush();
        var old = createAndSave();
        var updated = constructMultiple().get(0);

        post(url + "/id/" + old.getId(), updated);

        var found = findFirstThrowIfNotFound();
        assert found.equalz(updated);
    }

    protected abstract String getUrl();

    private static void failMvc(Exception e) {
        e.printStackTrace();
        failMvc();
    }

    private static void failMvc() {
        fail("MockMvc request failed");
    }

    private ResultActions get(String url, Object expected) {
        try {
            var res = mockMvc.perform(MockMvcRequestBuilders.get(url)).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
            if (expected != null) {
                res.andExpect(content().json(mapper.writeValueAsString(expected)));
            }
            return res;
        } catch (Exception e) {
            fail("MockMvc request failed");
            e.printStackTrace();
        }
        return null;
    }

    private ResultActions post(String url, Object content) {
        try {
            return mockMvc.perform(MockMvcRequestBuilders.post(url)
                    .content(mapper.writeValueAsString(content))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
            failMvc();
        }
        return null;
    }
}