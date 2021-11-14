package com.example.jungsunwoung.repository;

import com.example.jungsunwoung.entity.CalculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory,Long> {
}
