package theater.suites.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import theater.TestBase;

import java.util.List;
import java.util.function.Supplier;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public abstract class SpringTestBase extends TestBase {
    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper mapper = new ObjectMapper();

    protected <E> E eraseAndCreate(JpaRepository<E, Long> repository, Supplier<E> supplier) {
        repository.deleteAll();
        repository.flush();
        var entity = supplier.get();
        repository.saveAndFlush(entity);
        return entity;
    }

    protected <E> List<E> eraseAndCreateMultiple(JpaRepository<E, Long> repository, Supplier<List<E>> supplier) {
        repository.deleteAll();
        repository.flush();
        var entities = supplier.get();
        repository.saveAll(entities);
        repository.flush();
        return entities;
    }
}
