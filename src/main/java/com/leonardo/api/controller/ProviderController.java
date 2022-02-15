package com.leonardo.api.controller;

import com.leonardo.api.model.form.ProviderForm;
import com.leonardo.api.model.Provider;
import com.leonardo.api.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @GetMapping
    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }

    @GetMapping("/{id}")
    public Provider getProviderById(@PathVariable Long id) {
        return providerService.getProviderById(id);
    }

    @PostMapping
    public ResponseEntity<Provider> createProvider(@RequestBody @Valid ProviderForm providerForm, UriComponentsBuilder uriBuilder) {
        Provider provider = providerService.createProvider(providerForm);
        URI uri = uriBuilder.path("/provider/{id}").buildAndExpand(provider.getId()).toUri();
        return ResponseEntity.created(uri).body(provider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> changeProvider(@PathVariable Long id, @RequestBody @Valid ProviderForm providerForm) {
        Provider provider =  providerService.changeProvider(id, providerForm);
        return ResponseEntity.ok(provider);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProvider(@PathVariable Long id){
        return providerService.deleteProvider(id);
    }
}
