package com.ftn.sbnz.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ftn.sbnz.service.entity.FinalDecisionEntity;

@Repository
public interface FinalDecisionRepository extends JpaRepository<FinalDecisionEntity, Long> { 
    List<FinalDecisionEntity> findByUserIdOrderByDateDesc(Long userId);  

    void deleteByUserId(Long userId);
} 
