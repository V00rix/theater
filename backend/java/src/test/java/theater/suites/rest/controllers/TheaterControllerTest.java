package theater.suites.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Theater;
import theater.repositories.TheaterRepository;
import theater.suites.rest.ControllerTestBase;
import theater.utility.Dummy;

import java.util.List;

public class TheaterControllerTest extends ControllerTestBase<Theater> {
    private static final String url = "/api/theater";

    @Autowired
    TheaterRepository repository;

    @Override
    public JpaRepository<Theater, Long> getRepository() {
        return repository;
    }

    @Override
    public Theater construct() {
        return Dummy.theater();
    }

    @Override
    public List<Theater> constructMultiple() {
        return Dummy.theaters();
    }

    @Override
    protected String getUrl() {
        return url;
    }
}