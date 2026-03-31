package com.jobtracker.backend.controller;

import com.jobtracker.backend.model.Application;
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
}