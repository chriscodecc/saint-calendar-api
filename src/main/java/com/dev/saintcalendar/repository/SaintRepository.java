package com.dev.saintcalendar.repository;

import com.dev.saintcalendar.model.Saint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaintRepository extends CrudRepository<Saint, Long>{
    
    Page<Saint> findByDayAndMonth(int day, int month, Pageable p);
    Page<Saint> findByMonth(int month, Pageable p);
    Page<Saint> findByPatronageContainingIgnoreCase(String patronage, Pageable p);
}
