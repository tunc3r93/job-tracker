package com.jobtracker.backend.controller;

import com.jobtracker.backend.model.JobPosting;
import com.jobtracker.backend.repository.JobPostingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobPostingController {

    private final JobPostingRepository repository;

    public JobPostingController(JobPostingRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<JobPosting> getAllJobs() {
        return repository.findAll();
    }

    @PostMapping
    public JobPosting createJob(@RequestBody JobPosting job) {
        return repository.save(job);
    }
}