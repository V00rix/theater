package theater.suites.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Hall;
import theater.repositories.HallRepository;
import theater.suites.rest.ControllerTestBase;
import theater.utility.Dummy;

import java.util.List;

public class HallControllerTest extends ControllerTestBase<Hall> {
    private static final String url = "/api/hall";

    @Autowired
    HallRepository hallRepository;

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    protected JpaRepository<Hall, Long> getRepository() {
        return hallRepository;
    }

    @Override
    protected Hall construct() {
        return Dummy.hall();
    }

    @Override
    protected List<Hall> constructMultiple() {
        return Dummy.halls();
    }
}