package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.LeaveType;
import com.crm.crm_backend.repository.LeaveTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaveTypeServiceImpl {

    private final LeaveTypeRepository repository;

    public LeaveType save(LeaveType leaveType){

        if(leaveType.getId()!=null){

            LeaveType dbLeaveType = repository.findById(leaveType.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Leave Type not found."));

            dbLeaveType.setLeaveType(leaveType.getLeaveType());
            dbLeaveType.setShortName(leaveType.getShortName());
            dbLeaveType.setDescription(leaveType.getDescription());
            dbLeaveType.setActive(leaveType.getActive());

            dbLeaveType.setUpdatedBy(leaveType.getUpdatedBy());
            dbLeaveType.setUpdatedAt(LocalDateTime.now());

            return repository.save(dbLeaveType);

        }else{

            if(repository.existsByLeaveTypeIgnoreCase(leaveType.getLeaveType())){
                throw new RuntimeException("Leave Type already exists.");
            }

            leaveType.setCreatedAt(LocalDateTime.now());

            return repository.save(leaveType);

        }

    }

    public List<LeaveType> getAll(){

        return repository.findAll();

    }

    public LeaveType getById(Long id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave Type not found."));

    }

    public void delete(Long id){

        repository.deleteById(id);

    }

    public LeaveType changeStatus(Long id, Boolean active){

        LeaveType leaveType = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave Type not found."));

        leaveType.setActive(active);
        leaveType.setUpdatedAt(LocalDateTime.now());

        return repository.save(leaveType);

    }

}