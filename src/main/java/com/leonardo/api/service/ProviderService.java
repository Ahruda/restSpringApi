package com.leonardo.api.service;

import com.leonardo.api.model.Provider;
import com.leonardo.api.model.form.ProviderForm;
import com.leonardo.api.repository.ProviderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public Provider getProviderById(Long id) {
        Optional<Provider> optional = providerRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new NoSuchElementException("Element of id " + id + " does not exist");
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    @Transactional
    public Provider createProvider(ProviderForm providerForm) {
        Provider provider = providerForm.toProvider();
        providerRepository.save(provider);
        return provider;
    }

    @Transactional
    public Provider changeProvider(Long id, ProviderForm providerForm) {
        Provider provider = getProviderById(id);
        provider.setName(providerForm.getName());
        provider.setNumber(providerForm.getNumber());
        return provider;
    }

    @Transactional
    public ResponseEntity<String> deleteProvider(Long id) {
        getProviderById(id);
        providerRepository.deleteById(id);
        return ResponseEntity.ok("Provider of id " + id + " deleted with successfully");
    }
}
