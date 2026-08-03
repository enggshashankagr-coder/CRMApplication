package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.crm_backend.common.exception.ResourceNotFoundException;
import com.crm.crm_backend.entity.City;
import com.crm.crm_backend.repository.CityRepository;

@Service

public class CityServiceImpl {

	@Autowired
	private CityRepository cityRepository;

	public City save(City city) {

		if (city.getId() != null && city.getId() > 0) {

			Optional<City> existingCity = cityRepository.findById(city.getId());

			if (existingCity.isPresent()) {

				City dbCity = existingCity.get();

				dbCity.setCityCode(city.getCityCode());
				dbCity.setCityName(city.getCityName());
				dbCity.setState(city.getState());
				dbCity.setActive(city.getActive());

				dbCity.setUpdatedBy(city.getUpdatedBy());
				dbCity.setUpdatedAt(LocalDateTime.now());

				return cityRepository.save(dbCity);

			} else {
				throw new ResourceNotFoundException("City not found.");
			}

		} else {

			city.setCreatedAt(LocalDateTime.now());

			return cityRepository.save(city);
		}

	}

	public List<City> getAll() {
		return cityRepository.findAll();
	}

	public City getById(Long id) {

		return cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("City not found."));
	}

	public List<City> getByState(Long stateId) {
		return cityRepository.findByStateId(stateId);
	}

	public void delete(Long id) {

		cityRepository.deleteById(id);

	}
}
