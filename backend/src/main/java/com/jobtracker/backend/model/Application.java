package com.jobtracker.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;
    private String jobTitle;
    private String company;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status; // OFFEN, VERSCHICKT, ABGELEHNT

    private LocalDateTime createdAt = LocalDateTime.now();
}