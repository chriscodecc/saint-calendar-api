package com.dev.saintcalendar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.saintcalendar.dto.SaintRequest;
import com.dev.saintcalendar.exeption.ExeptionHandelingController;
import com.dev.saintcalendar.model.Saint;
import com.dev.saintcalendar.repository.SaintRepository;

@Service
public class SaintService {

    private final SaintRepository repository;

    public SaintService(SaintRepository repository){
        this.repository = repository;
    }

    public Saint saveSaint(SaintRequest saintRequest) {
        Saint savedSaint = new Saint(saintRequest.name(),
                                        saintRequest.day(),
                                        saintRequest.month(),
                                        saintRequest.patronage(),
                                        saintRequest.description(),
                                        saintRequest.tropar(),
                                        saintRequest.kondak(),
                                        saintRequest.isMartyr());        
        repository.save(savedSaint);
        return savedSaint;
    }

    public Page<Saint> findByMonth(int month, Pageable p){
        if (month > 0 && month <= 12) {
            return repository.findByMonth(month, p);
        } throw new IllegalArgumentException("Month must be between 1 and 12");
    }

    public Page<Saint> findByPatronageContainingIgnoreCase(String patronage, Pageable p){
        return repository.findByPatronageContainingIgnoreCase(patronage, p);
    }
    
}
