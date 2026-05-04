package com.dev.saintcalendar.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.dev.saintcalendar.dto.QuoteRequest;
import com.dev.saintcalendar.dto.SaintRequest;
import com.dev.saintcalendar.model.Quote;
import com.dev.saintcalendar.model.Saint;
import com.dev.saintcalendar.repository.SaintRepository;

@Service
public class SaintService {

    private final SaintRepository repository;

    public SaintService(SaintRepository repository){
        this.repository = repository;
    }


    @Transactional
    public Saint saveSaint(SaintRequest saintRequest) {
        Saint saint = new Saint();

        saint.setName(saintRequest.name());
        saint.setDay(saintRequest.day());
        saint.setMonth(saintRequest.month());
        saint.setPatronage(saintRequest.patronage());
        saint.setDescription(saintRequest.description());
        saint.setTropar(saintRequest.tropar());
        saint.setKondak(saintRequest.kondak());
        saint.setMartyr(saintRequest.isMartyr());    
            
        if (saint.getQuotes() == null) {
            saint.setQuotes(new ArrayList<>());
        }

        //Attach all quotes in memory using your Helper Method
        if (saintRequest.quotes() != null) {
            for (QuoteRequest qRequest : saintRequest.quotes()) {
                Quote quote = new Quote();
                quote.setText(qRequest.text());
                quote.setSource(qRequest.source());
                
                // This links them perfectly in Java memory
                saint.addQuote(quote); 
            }
        }
        
        return repository.save(saint);  
    }

    public List<Saint> findByMonth(int month){
        if (month > 0 && month <= 12) {
            return repository.findByMonth(month);
        } throw new IllegalArgumentException("Month must be between 1 and 12");
    }

    public List<Saint> findByPatronageContainingIgnoreCase(String patronage){
        return repository.findByPatronageContainingIgnoreCase(patronage);
    }

    public List<Saint> findeAll(){
        return repository.findAllByOrderByMonthAsc();
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public Saint findById(Long saintId) {
        return repository.findById(saintId).orElse(null);
    }
    
}
