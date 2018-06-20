package theater.suites.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Client;
import theater.repositories.ClientRepository;
import theater.suites.rest.ControllerTestBase;
import theater.utility.Dummy;

import java.util.List;

public class ClientControllerTest extends ControllerTestBase<Client> {
    private static final String url = "/api/client";

    @Autowired
    ClientRepository clientRepository;

    @Override
    protected String getUrl() {
        return url;
    }

    @Override
    protected JpaRepository<Client, Long> getRepository() {
        return clientRepository;
    }

    @Override
    protected Client construct() {
        return Dummy.client();
    }

    @Override
    protected List<Client> constructMultiple() {
        return Dummy.clients();
    }
}