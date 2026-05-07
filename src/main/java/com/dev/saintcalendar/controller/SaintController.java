package com.dev.saintcalendar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dev.saintcalendar.dto.SaintRequest;
import com.dev.saintcalendar.model.Saint;
import com.dev.saintcalendar.service.SaintService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;




@RestController
@RequestMapping("/api/saints")
public class SaintController {

    private static final Logger logger = LoggerFactory.getLogger(SaintController.class);
    private final SaintService service;
    private final String EXPECTED_TOKEN = "my-super-secret-key-123";

    public SaintController(SaintService service){
        this.service = service;
    }
    

    @PostMapping
    public ResponseEntity<Saint> createNewSaint(
        @RequestHeader(value = "Authorization", required = false) String authHeader,
        @Valid @RequestBody SaintRequest saintRequest) throws MethodArgumentNotValidException{

            if(authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.replace("Bearer ", "");
            }

            if(authHeader == null || !authHeader.equals(EXPECTED_TOKEN)){
                System.out.println("SECURITY ALERT: Blocked an unauthorized request! " + authHeader );
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

        System.out.println("DEBUG JAVA RECEIVED AUTHORIZED DATA: " + saintRequest.name());
        Saint saved = service.saveSaint(saintRequest);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{month}")
    public List<Saint> findByMonth(@PathVariable int month){
        return service.findByMonth(month);
    }

    @GetMapping("/search")
    public List<Saint> findByPatronageContainingIgnoreCase(@RequestParam String keyword) {
         return service.findByPatronageContainingIgnoreCase(keyword);
    }

    @GetMapping("")
    public List<Saint> gettAllSaints() {
        return service.findeAll();
    }

    /**
     * returns the deletet Saint if exists
     * else null
    **/
    @DeleteMapping("/delete/{saintId}")
    public String deleteSaint(@PathVariable long saintId) {
        try {
            service.deleteById((long)saintId);
            return "User " + saintId + " deleted successfully!";
        }
        catch(Exception  e) {
            return "ERROR: Could NOT deleted " + saintId;
        }
    
    }
    
    
    
}
