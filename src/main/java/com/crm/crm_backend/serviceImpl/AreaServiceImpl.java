package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.Area;
import com.crm.crm_backend.repository.AreaRepository;

@Service
public class AreaServiceImpl {
	
	@Autowired
	private  AreaRepository areaRepository;

	    public Area save(Area area) {

	        if (area.getId() != null) {

	            Area dbArea = areaRepository.findById(area.getId())
	                    .orElseThrow(() -> new RuntimeException("Area not found."));

	            dbArea.setAreaCode(area.getAreaCode());
	            dbArea.setAreaName(area.getAreaName());
	            dbArea.setCity(area.getCity());
	            dbArea.setActive(area.getActive());

	            dbArea.setUpdatedBy(area.getUpdatedBy());
	            dbArea.setUpdatedAt(LocalDateTime.now());

	            return areaRepository.save(dbArea);

	        } else {

	            if (areaRepository.existsByAreaCode(area.getAreaCode())) {
	                throw new RuntimeException("Area Code already exists.");
	            }

	            if (areaRepository.existsByAreaName(area.getAreaName())) {
	                throw new RuntimeException("Area Name already exists.");
	            }

	            area.setCreatedAt(LocalDateTime.now());

	            return areaRepository.save(area);
	        }
	    }

	    public List<Area> getAll() {
	        return areaRepository.findAll();
	    }

	    public Area getById(Long id) {

	        return areaRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Area not found."));
	    }

	    public List<Area> getByCity(Long cityId) {
	        return areaRepository.findByCityId(cityId);
	    }

	    public void delete(Long id) {

	        Area area = getById(id);

	        areaRepository.delete(area);
	    }
}
