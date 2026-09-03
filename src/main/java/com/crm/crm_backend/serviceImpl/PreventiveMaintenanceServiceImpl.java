package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.PreventiveMaintenance;
import com.crm.crm_backend.repository.PreventiveMaintenanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreventiveMaintenanceServiceImpl {

    private final PreventiveMaintenanceRepository repository;

    public PreventiveMaintenance save(
            PreventiveMaintenance maintenance){

        if(maintenance.getId()!=null){

            PreventiveMaintenance dbMaintenance =
                    repository.findById(maintenance.getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Preventive Maintenance not found."));

            dbMaintenance.setPreventiveMaintenance(
                    maintenance.getPreventiveMaintenance());

            dbMaintenance.setPreventiveMaintenanceCode(
                    maintenance.getPreventiveMaintenanceCode());

			/*
			 * dbMaintenance.setDescription( maintenance.getDescription());
			 */

            dbMaintenance.setActive(
                    maintenance.getActive());

            dbMaintenance.setUpdatedBy(
                    maintenance.getUpdatedBy());

            dbMaintenance.setUpdatedAt(
                    LocalDateTime.now());

            return repository.save(dbMaintenance);

        }else{

            if(repository.existsByPreventiveMaintenanceIgnoreCase(
                    maintenance.getPreventiveMaintenance())){

                throw new RuntimeException(
                        "Preventive Maintenance already exists.");
            }

            if(repository.existsByPreventiveMaintenanceCodeIgnoreCase(
                    maintenance.getPreventiveMaintenanceCode())){

                throw new RuntimeException(
                        "Preventive Maintenance Code already exists.");
            }

            maintenance.setCreatedAt(LocalDateTime.now());

            return repository.save(maintenance);

        }

    }

    public List<PreventiveMaintenance> getAll(){

        return repository.findAll();

    }

    public PreventiveMaintenance getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Preventive Maintenance not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public PreventiveMaintenance changeStatus(
            Long id,
            Boolean active){

        PreventiveMaintenance maintenance =
                repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Preventive Maintenance not found."));

        maintenance.setActive(active);
        maintenance.setUpdatedAt(LocalDateTime.now());

        return repository.save(maintenance);

    }

}