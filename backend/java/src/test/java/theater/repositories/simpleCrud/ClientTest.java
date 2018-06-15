package theater.repositories.simpleCrud;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import theater.domain.exceptions.NotImplementedException;
import theater.repositories.ClientRepository;
import theater.utility.Dummy;
import theater.utility.EntityTestBase;
import theater.utility.JpaTestBase;

public class ClientTest extends JpaTestBase implements CRUDTest {

    //region Autowired
    @Autowired
    ClientRepository clientRepository;
    //endregion

    @Before
    public void setUp() {
        clientRepository.deleteAll();
    }

    @Override
    @Test
    public void create() {
        setUp();
        var client = Dummy.client();
        clientRepository.save(client);
    }

    @Override
    @Test
    public void read() {
        EntityTestBase.findAllAndPrint(clientRepository);
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
        clientRepository.deleteAll();
        var client = Dummy.client();
        clientRepository.save(client);
        var found = EntityTestBase.findAllAndPrint(clientRepository);
        assert client.equalz(found.get(0));
    }

}
