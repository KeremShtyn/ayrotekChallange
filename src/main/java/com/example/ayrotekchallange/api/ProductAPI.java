package com.example.ayrotekchallange.api;

import com.example.ayrotekchallange.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ayrotekchallange.constants.AyrotekApiEndpoint.PRODUCT_PATH;

@CrossOrigin(origins = "*")
@RequestMapping(PRODUCT_PATH)
public interface ProductAPI {

    @GetMapping("{id}")
    public ProductDTO getById(@PathVariable("id") String id);

    @PostMapping("/{id}")
    public ProductDTO save(@PathVariable String clientId, @RequestBody ProductDTO productDTO);

    @PutMapping("/{id}")
    public ProductDTO update(@RequestHeader("clientId") String clientId,@RequestBody ProductDTO productDTO);

    @GetMapping
    public List<ProductDTO> getAll();

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader("clientId") String clientId, @PathVariable("id") String id);

}
