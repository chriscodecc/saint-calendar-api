package com.dev.saintcalendar.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.dev.saintcalendar.dto.SaintRequest;
import com.dev.saintcalendar.model.Saint;
import com.dev.saintcalendar.repository.SaintRepository;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentMatchers;

public class SaintServiceTest {
    
    @Test
    public void testsaveSaint(){
        SaintRepository mockRep = Mockito.mock(SaintRepository.class);
        SaintService service = new SaintService(mockRep);
        Saint resultSaint = new Saint();

        SaintRequest saintRequest = new SaintRequest("St. Test", 1, 1, "testpat", "He is awesome!", "Some lalala Tropar", "Some Kondak", false, null, null, null, null);
        Saint saint = new Saint();
        saint.setName("St. Test");
        saint.setDay(1);
        saint.setMonth(1);
        saint.setPatronage("testpat");
        saint.setDescription("He is awesome!");
        saint.setTropar("Some lalala Tropar");
        saint.setKondak("Some Kondak");
        saint.setMartyr(false);
        
        when(mockRep.save(ArgumentMatchers.any(Saint.class))).thenReturn(saint);
        resultSaint = service.saveSaint(saintRequest);
        
        Assertions.assertEquals(saint, resultSaint);

    }
}
