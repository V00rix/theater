package theater.suites.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Performance;
import theater.repositories.PerformanceRepository;
import theater.suites.rest.ControllerTestBase;
import theater.utility.Dummy;

import java.util.List;

public class PerformanceControllerTest extends ControllerTestBase<Performance> {
    private static final String url = "/api/performance";

    @Autowired
    PerformanceRepository repository;

    @Override
    protected JpaRepository<Performance, Long> getRepository() {
        return repository;
    }

    @Override
    protected Performance construct() {
        return Dummy.performance();
    }

    @Override
    protected List<Performance> constructMultiple() {
        return Dummy.performances();
    }

    @Override
    protected String getUrl() {
        return url;
    }
}