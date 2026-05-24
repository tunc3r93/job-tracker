package com.jobtracker.backend.controller;

import com.jobtracker.backend.model.JobPosting;
import com.jobtracker.backend.repository.JobPostingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(JobPostingController.class)
@DisplayName("JobPostingController Unit Tests")
class JobPostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private JobPosting testJob;

    @BeforeEach
    void setUp() {
        testJob = new JobPosting();
        testJob.setId(1L);
        testJob.setTitle("Java Developer");
        testJob.setCompany("Tech Corp");
        testJob.setDescription("Senior Java Developer fuer Backend-Entwicklung");
        testJob.setCreatedAt(LocalDateTime.now());
    }

    @Test
    @DisplayName("GET /jobs sollte alle Jobs zurückgeben")
    void testGetAllJobs_Success() throws Exception {
        List<JobPosting> jobs = Arrays.asList(testJob);
        when(jobPostingRepository.findAll()).thenReturn(jobs);

        mockMvc.perform(get("/jobs")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].title", equalTo("Java Developer")))
            .andExpect(jsonPath("$[0].company", equalTo("Tech Corp")));

        verify(jobPostingRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("POST /jobs sollte einen neuen Job erstellen")
    void testCreateJob_Success() throws Exception {
        JobPosting newJob = new JobPosting();
        newJob.setTitle("Python Developer");
        newJob.setCompany("Data Corp");

        JobPosting savedJob = new JobPosting();
        savedJob.setId(2L);
        savedJob.setTitle("Python Developer");
        savedJob.setCompany("Data Corp");
        savedJob.setCreatedAt(LocalDateTime.now());

        when(jobPostingRepository.save(any(JobPosting.class))).thenReturn(savedJob);

        mockMvc.perform(post("/jobs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newJob)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title", equalTo("Python Developer")))
            .andExpect(jsonPath("$.company", equalTo("Data Corp")));

        verify(jobPostingRepository, times(1)).save(any(JobPosting.class));
    }

    @Test
    @DisplayName("GET /jobs mit leerer Liste")
    void testGetAllJobs_Empty() throws Exception {
        when(jobPostingRepository.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/jobs")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }
}
