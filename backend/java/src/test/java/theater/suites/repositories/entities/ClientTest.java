package theater.suites.repositories.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import theater.domain.entities.Client;
import theater.repositories.ClientRepository;
import theater.suites.repositories.EntityTestBase;
import theater.utility.Dummy;

import java.util.List;

public class ClientTest extends EntityTestBase<Client> {

    @Autowired
    ClientRepository clientRepository;

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
