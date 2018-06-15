package theater.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theater.domain.entities.Client;
import theater.repositories.ClientRepository;

@RestController
@RequestMapping("/api/client")
public class ClientController extends ControllerBase<Client, ClientRepository> {
    private final
    ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientRepository repository() {
        return clientRepository;
    }
}
