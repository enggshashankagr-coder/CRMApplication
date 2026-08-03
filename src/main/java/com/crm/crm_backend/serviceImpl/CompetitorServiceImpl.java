package com.crm.crm_backend.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.crm_backend.entity.Competitor;
import com.crm.crm_backend.repository.CompetitorRepository;

@Service
public class CompetitorServiceImpl {
	
	@Autowired
	private  CompetitorRepository competitorRepository;

    public Competitor save(Competitor competitor){

        if(competitor.getId()!=null){

            Competitor dbCompetitor = competitorRepository.findById(competitor.getId())
                    .orElseThrow(() -> new RuntimeException("Competitor not found."));

            dbCompetitor.setCompetitorName(competitor.getCompetitorName());
            dbCompetitor.setAddress(competitor.getAddress());
            dbCompetitor.setPrice(competitor.getPrice());
            dbCompetitor.setAmc(competitor.getAmc());
            dbCompetitor.setActive(competitor.getActive());
            dbCompetitor.setUpdatedBy(competitor.getUpdatedBy());
            dbCompetitor.setUpdatedAt(LocalDateTime.now());

            return competitorRepository.save(dbCompetitor);

        }else{

            if(competitorRepository.existsByCompetitorNameIgnoreCase(
                    competitor.getCompetitorName())){
                throw new RuntimeException("Competitor already exists.");
            }

            competitor.setCreatedAt(LocalDateTime.now());

            return competitorRepository.save(competitor);
        }

    }

    public List<Competitor> getAll(){
        return competitorRepository.findAll();
    }

    public Competitor getById(Long id){

        return competitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competitor not found."));
    }

    public void delete(Long id){

        competitorRepository.deleteById(id);

    }
    public Competitor changeStatus(Long id, Boolean active) {

        Competitor competitor = competitorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competitor not found."));

        competitor.setActive(active);
        competitor.setUpdatedAt(LocalDateTime.now());

        return competitorRepository.save(competitor);
    }  
    
    
}
