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
        Saint s1 = new Saint( 
            "test1",
            1,
            2,
            "patronage",
            "seom text",
            "tropar",
            "kondak",
            true);

        Saint s2 = new Saint( 
            "test2",
            2,
            2,
            "patronage2",
            "seom text2",
            "tropar2",
            "kondak2",
            false);

        List<Saint> saintList = new ArrayList<Saint>();
        saintList.add(s1);
        saintList.add(s2);
/**
        when(service.findByMonth(12)).thenReturn(saintList);

        List<Saint> result = service.findByMonth((12));
        assertEquals(saintList, result);**/
    }
    
}
