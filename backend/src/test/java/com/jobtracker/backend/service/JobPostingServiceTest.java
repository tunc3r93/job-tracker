package com.jobtracker.backend.service;

import com.jobtracker.backend.model.JobPosting;
import com.jobtracker.backend.repository.JobPostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("JobPostingService Unit Tests")
class JobPostingServiceTest {

    @Mock
    private JobPostingRepository jobPostingRepository;

    @InjectMocks
    private JobPostingService jobPostingService;

    private JobPosting testJob;

    @BeforeEach
    void setUp() {
        testJob = new JobPosting();
        testJob.setId(1L);
        testJob.setTitle("Java Developer");
        testJob.setCompany("Tech Corp");
        testJob.setDescription("Senior Java Position");
        testJob.setCreatedAt(LocalDateTime.now());
    }

    @Test
    @DisplayName("Service.getAllJobs() sollte alle Jobs holen")
    void testGetAllJobs() {
        List<JobPosting> jobs = Arrays.asList(testJob);
        when(jobPostingRepository.findAll()).thenReturn(jobs);

        List<JobPosting> result = jobPostingService.getAllJobs();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Java Developer", result.get(0).getTitle());
        verify(jobPostingRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Service.createJob() speichert und gibt Job zurück")
    void testCreateJob() {
        JobPosting newJob = new JobPosting();
        newJob.setTitle("Python Developer");
        newJob.setCompany("Data Corp");

        when(jobPostingRepository.save(any(JobPosting.class))).thenReturn(testJob);

        JobPosting result = jobPostingService.createJob(newJob);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(jobPostingRepository, times(1)).save(any(JobPosting.class));
    }

    @Test
    @DisplayName("Service.createJob() mit null wirft Exception")
    void testCreateJob_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            jobPostingService.createJob(null);
        });

        verify(jobPostingRepository, never()).save(any());
    }
}
