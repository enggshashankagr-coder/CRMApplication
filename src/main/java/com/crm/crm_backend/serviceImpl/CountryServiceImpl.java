package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.Country;
import com.crm.crm_backend.repository.CountryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl {

    private final CountryRepository countryRepository;

    public Country save(Country country) {

        if (country.getId() != null && country.getId() > 0) {

            Optional<Country> existingCountry =
                    countryRepository.findById(country.getId());

            if (existingCountry.isPresent()) {

                Country dbCountry = existingCountry.get();

                dbCountry.setCountryCode(country.getCountryCode());
                dbCountry.setCountryName(country.getCountryName());
                dbCountry.setActive(country.getActive());

                dbCountry.setUpdatedBy(country.getUpdatedBy());
                dbCountry.setUpdatedAt(LocalDateTime.now());

                return countryRepository.save(dbCountry);

            } else {
                throw new RuntimeException("Country not found.");
            }

        } else {

            country.setCreatedAt(LocalDateTime.now());

            return countryRepository.save(country);
        }
    }

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found."));
    }

    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}