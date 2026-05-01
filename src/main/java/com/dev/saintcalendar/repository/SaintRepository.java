package com.dev.saintcalendar.repository;

import com.dev.saintcalendar.model.Saint;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaintRepository extends CrudRepository<Saint, Long>{
    
    List<Saint> findByDayAndMonth(int day, int month);
    List<Saint> findByMonth(int month);
    List<Saint> findByPatronageContainingIgnoreCase(String patronage);
    List<Saint> findAllByOrderByMonthAsc();
}
