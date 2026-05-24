package com.jobtracker.backend.service;

import com.jobtracker.backend.model.JobPosting;
import com.jobtracker.backend.repository.JobPostingRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public JobPostingService(JobPostingRepository jobPostingRepository) {
        this.jobPostingRepository = jobPostingRepository;
    }

    public List<JobPosting> getAllJobs() {
        return jobPostingRepository.findAll();
    }

    public Optional<JobPosting> getJobById(Long id) {
        return jobPostingRepository.findById(id);
    }

    public JobPosting createJob(JobPosting jobPosting) {
        if (jobPosting == null) {
            throw new NullPointerException("JobPosting darf nicht null sein");
        }
        return jobPostingRepository.save(jobPosting);
    }

    public void deleteJob(Long id) {
        jobPostingRepository.deleteById(id);
    }

    public JobPosting updateJob(Long id, JobPosting updatedJob) {
        return jobPostingRepository.findById(id)
            .map(existingJob -> {
                existingJob.setTitle(updatedJob.getTitle());
                existingJob.setCompany(updatedJob.getCompany());
                existingJob.setDescription(updatedJob.getDescription());
                return jobPostingRepository.save(existingJob);
            })
            .orElseThrow(() -> new RuntimeException("Job mit ID " + id + " nicht gefunden"));
    }

    public long countAllJobs() {
        return jobPostingRepository.count();
    }
}
