package com.LittleDelhi.LittleDelhiBackend.repository;

import com.LittleDelhi.LittleDelhiBackend.model.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {
    List<TimeSheet> findByUserId(Long userId);
}
