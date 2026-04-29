package com.dev.saintcalendar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dev.saintcalendar.dto.SaintRequest;
import com.dev.saintcalendar.model.Saint;
import com.dev.saintcalendar.repository.SaintRepository;
import com.dev.saintcalendar.service.SaintService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;




@RestController
@RequestMapping("/api/saints")
public class SaintController {

    private static final Logger logger = LoggerFactory.getLogger(SaintController.class);
    private final SaintService service;

    public SaintController(SaintService service){
        this.service = service;
    }
    

    @PostMapping
    public ResponseEntity<Saint> createNewSaint(@Valid @RequestBody SaintRequest saintRequest) {
        Saint saved = service.saveSaint(saintRequest);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{month}")
    public Page<Saint> findByMonth(@PathVariable int month, Pageable p) {
        return service.findByMonth(month, p);
    }

    @GetMapping("/search")
    public Page<Saint> findByPatronageContainingIgnoreCase(@RequestParam String keyword, Pageable p) {
         return service.findByPatronageContainingIgnoreCase(keyword, p);
    }

    @GetMapping("")
    public List<Saint> gettAllSaints() {
        return service.findeAll();
    }
    
    
    
    
    
}
