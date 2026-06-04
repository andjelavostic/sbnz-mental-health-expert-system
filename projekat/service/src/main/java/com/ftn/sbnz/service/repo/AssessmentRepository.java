package com.ftn.sbnz.service.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz.service.entity.AssessmentEntity;

@Repository
public interface AssessmentRepository extends JpaRepository<AssessmentEntity, Long> {
    // Vraća sve unose za tog korisnika koji su noviji od datuma 'time'
    List<AssessmentEntity> findByUserIdAndTimestampAfter(Long userId, LocalDateTime time);
}