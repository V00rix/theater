package theater.repositories.simpleCrud;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import theater.domain.exceptions.NotImplementedException;
import theater.repositories.CRUDTests;
import theater.repositories.ClientsRepository;
import theater.utility.Dummy;
import theater.utility.EntityTestBase;
import theater.utility.JpaTestBase;

public class ClientTest extends JpaTestBase implements CRUDTests {

    //region Autowired
    @Autowired
    ClientsRepository clientsRepository;
    //endregion

    @Before
    public void setUp() {
        clientsRepository.deleteAll();
    }

    @Override
    @Test
    public void create() {
        setUp();
        var client = Dummy.client();
        clientsRepository.save(client);
    }

    @Override
    @Test
    public void read() {
        EntityTestBase.findAllAndPrint(clientsRepository);
    }

    @Override
    @Test
    public void update() {
        throw new NotImplementedException();
    }

    @Override
    @Test
    public void delete() {
        throw new NotImplementedException();
    }

    @Override
    @Test
    public void createAndRead() {
        clientsRepository.deleteAll();
        var client = Dummy.client();
        clientsRepository.save(client);
        var found = EntityTestBase.findAllAndPrint(clientsRepository);
        assert client.equals(found.get(0));
    }

}
