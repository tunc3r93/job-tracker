package com.jobtracker.backend.repository;

import com.jobtracker.backend.model.Application;
import com.jobtracker.backend.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatus(ApplicationStatus status);

    List<Application> findByCompany(String company);

    List<Application> findByStatusAndCompany(ApplicationStatus status, String company);
}

