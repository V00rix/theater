package theater.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import theater.domain.entities.Client;
import theater.repositories.ClientRepository;
import theater.rest.ControllerBase;

@RestController
@RequestMapping("/api/client")
public class ClientController extends ControllerBase<Client, ClientRepository> {

    public ClientController(ClientRepository clientRepository) {
        super(clientRepository);
    }
}
