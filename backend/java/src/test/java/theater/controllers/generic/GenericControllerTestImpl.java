package theater.controllers.generic;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import theater.domain.entities.Hall;
import theater.repositories.HallRepository;
import theater.utility.Dummy;

@Ignore("later..")
public class GenericControllerTestImpl extends GenericControllerTest<Hall, HallRepository> {
    private static final String url = "/api/session/";

    @Autowired
    HallRepository hallRepository;

    @Override
    protected String url() {
        return url;
    }

    @Override
    protected HallRepository repository() {
        return hallRepository;
    }

    @Override
    protected Hall entity() {
        return Dummy.hall();
    }

    @Override
    protected Hall[] multiple() {
        return new Hall[0];
    }
}
