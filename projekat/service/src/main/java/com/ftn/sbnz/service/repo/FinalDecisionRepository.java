package com.ftn.sbnz.service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ftn.sbnz.service.entity.FinalDecisionEntity;

@Repository
public interface FinalDecisionRepository extends JpaRepository<FinalDecisionEntity, Long> {   
} 
