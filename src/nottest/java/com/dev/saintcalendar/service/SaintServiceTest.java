package com.dev.saintcalendar.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dev.saintcalendar.model.Saint;
import com.dev.saintcalendar.repository.SaintRepository;

@ExtendWith(MockitoExtension.class)
public class SaintServiceTest {

    @Mock
    SaintRepository repository;

    @InjectMocks
    SaintService service;

    @Test
    void shouldThrowExceptionWhenMonthIsInvalid(){
        //assertThrows(IllegalArgumentException.class, () -> service.findByMonth(13));
    }

    @Test
    void shouldReturnListOfSaintsWhenMonthIsValid(){
         Saint saint = new Saint();
        saint.setName("Saint Nicholas");
        saint.setDay(6);
        saint.setMonth(12);
        saint.setPatronage("Sailors");
        saint.setDescription("The Bishop of Myra.");
        saint.setTropar("The truth of things...");
        saint.setKondak("In Myra, O Holy One...");
        saint.setMartyr(false);

         Saint saint2 = new Saint();
        saint.setName("Saint Nicholas2");
        saint.setDay(2);
        saint.setMonth(12);
        saint.setPatronage("Sailors2");
        saint.setDescription("The Bishop of Myra.2");
        saint.setTropar("The truth of things..22.");
        saint.setKondak("In Myra, O Holy On22...");
        saint.setMartyr(true);

        List<Saint> saintList = new ArrayList<Saint>();
        saintList.add(saint);
        saintList.add(saint2);
/**
        when(service.findByMonth(12)).thenReturn(saintList);

        List<Saint> result = service.findByMonth((12));
        assertEquals(saintList, result);**/
    }
    
}
