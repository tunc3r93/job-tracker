package com.jobtracker.backend.controller;

import com.jobtracker.backend.model.Application;
import com.jobtracker.backend.model.ApplicationStatus;
import com.jobtracker.backend.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@CrossOrigin(origins = "http://localhost:3000") // Für React Frontend
public class ApplicationController {

    @Autowired
    private ApplicationRepository repository;

    @GetMapping
    public List<Application> getAllApplications() {
        return repository.findAll();
    }

    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return repository.save(application);
    }

    @GetMapping("/filter")
    public List<Application> filterApplications(
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(required = false) String company
    ) {
        if (status != null && company != null) {
            return repository.findByStatusAndCompany(status, company);
        } else if (status != null) {
            return repository.findByStatus(status);
        } else if (company != null) {
            return repository.findByCompany(company);
        } else {
            return repository.findAll();
        }
    }
}