package br.com.syonet.newsletters.resources;

import br.com.syonet.newsletters.dtos.ClienteDTO;
import br.com.syonet.newsletters.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteResource {

    private final ClienteService service;

    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO>cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok().body(this.service.cadastroCliente(clienteDTO));
    }
}
