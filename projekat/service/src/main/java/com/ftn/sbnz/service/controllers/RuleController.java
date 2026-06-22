package com.ftn.sbnz.service.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.assessment.UserAssessment;
import com.ftn.sbnz.model.decision.FinalDecision;
import com.ftn.sbnz.service.dto.FinalDecisionDTO;
import com.ftn.sbnz.service.entity.FinalDecisionEntity;
import com.ftn.sbnz.service.services.RuleEngineService;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    private final RuleEngineService service;

    public RuleController(RuleEngineService service) {
        this.service = service;
    }

    @PostMapping("/evaluate")
    public FinalDecision evaluate(@RequestBody UserAssessment input) {
        return service.evaluate(input);
    }

    @GetMapping("/history/{userId}")
    public List<FinalDecisionDTO> getHistory(@PathVariable Long userId) {
        return service.getHistory(userId);
    }
}