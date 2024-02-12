package com.example.ayrotekchallange.api;

import com.example.ayrotekchallange.dto.ClientDTO;
import org.springframework.web.bind.annotation.*;
import static com.example.ayrotekchallange.constants.AyrotekApiEndpoint.CLIENT_PATH;


@CrossOrigin(origins = "*")
@RequestMapping(CLIENT_PATH)
public interface ClientAPI {

    @GetMapping("{id}")
    public ClientDTO getById(@PathVariable("id") String id);

    @PostMapping
    public ClientDTO save(@RequestBody ClientDTO clientDTO);

    @PutMapping("/{id}")
    public ClientDTO update(@RequestBody ClientDTO clientDTO);
}
