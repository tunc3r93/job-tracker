# Backend-Architektur 🏗️

## Überblick

Der Job Tracker folgt einer **mehrstufigen Schichtenarchitektur** (Layered Architecture), um sauberen, wartbaren Code zu gewährleisten.

```
┌─────────────────────────────────────┐
│      Frontend (React)               │
│  Benutzerinterface                  │
└──────────────┬──────────────────────┘
               │ HTTP REST API
┌──────────────▼──────────────────────┐
│      Controller Layer               │ ← API Endpoints
├─────────────────────────────────────┤
│      Service Layer                  │ ← Business Logic
├─────────────────────────────────────┤
│      Repository Layer               │ ← Data Access
├─────────────────────────────────────┤
│      Database (PostgreSQL/MySQL)    │ ← Persistierung
└─────────────────────────────────────┘
```

---

## 🎯 Die 5 Schichten im Detail

### 1️⃣ Controller (API-Schicht)

**Aufgabe:** HTTP-Anfragen verarbeiten und Responses zurückgeben

**Regeln:**
- ❌ Keine Business-Logik
- ✅ Nur HTTP-Handling
- ✅ Input validieren
- ✅ Responses formatieren

**Beispiel:**

```java
@RestController
@RequestMapping(“/api/jobs”)
public class JobController {
    
    private final JobService jobService;
    
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    
    // GET /api/jobs
    @GetMapping
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        List<JobDTO> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }
    
    // POST /api/jobs
    @PostMapping
    public ResponseEntity<JobDTO> createJob(@RequestBody CreateJobDTO request) {
        JobDTO newJob = jobService.createJob(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newJob);
    }
}
```

**Was passiert hier:**
1. Frontend sendet HTTP POST mit Job-Daten
2. Controller empfängt die Anfrage
3. **Keine Validierung oder Logik im Controller!**
4. Service wird aufgerufen mit den Daten
5. Response wird formatiert und zurückgesendet

---

### 2️⃣ Service (Business-Logik)

**Aufgabe:** Die echte Logik der Anwendung

**Hier werden Entscheidungen getroffen:**
- Sind die Daten gültig?
- Darf dieser Nutzer das tun?
- Was muss in der Datenbank passieren?

**Beispiel:**

```java
@Service
public class JobService {
    
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    
    public JobService(JobRepository jobRepository, 
                     ApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }
    
    public JobDTO createJob(CreateJobDTO request) {
        // ✅ VALIDIERUNG
        if (request.getTitle() == null || request.getTitle().isBlank()) {
            throw new InvalidJobException(“Jobtitel ist erforderlich”);
        }
        
        // ✅ BUSINESS LOGIC
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setCompany(request.getCompany());
        job.setStatus(JobStatus.OPEN); // Standard-Status
        job.setCreatedAt(LocalDateTime.now());
        
        // ✅ PERSISTIEREN
        Job savedJob = jobRepository.save(job);
        
        // ✅ IN DTO UMWANDELN
        return JobDTO.fromEntity(savedJob);
    }
    
    public void applyToJob(Long jobId, Long userId) {
        Job job = jobRepository.findById(jobId)
            .orElseThrow(() -> new JobNotFoundException(“Job nicht gefunden”));
        
        // Check: Wurde schon beworben?
        boolean alreadyApplied = applicationRepository
            .existsByJobAndUserId(job, userId);
        
        if (alreadyApplied) {
            throw new DuplicateApplicationException(
                “Du hast dich bereits auf diesen Job beworben”);
        }
        
        // Neue Bewerbung erstellen
        Application application = new Application();
        application.setJob(job);
        application.setUserId(userId);
        application.setStatus(ApplicationStatus.PENDING);
        applicationRepository.save(application);
    }
}
```

**Was macht der Service:**
- ✅ Validiert Eingaben
- ✅ Prüft Geschäftsregeln
- ✅ Koordiniert mehrere Repositories
- ✅ Entscheidungen treffen (z.B. Standard-Werte)

---

### 3️⃣ Repository (Datenzugriff)

**Aufgabe:** Kommunikation mit der Datenbank

**Spring Data JPA macht das einfach:**

```java
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    
    // Standard-Methoden (sind schon da!)
    // - findById(Long id)
    // - findAll()
    // - save(Job job)
    // - delete(Job job)
    
    // CUSTOM Queries
    List<Job> findByStatusAndCompany(JobStatus status, String company);
    
    List<Job> findByCreatedAtAfter(LocalDateTime date);
    
    @Query(“SELECT j FROM Job j WHERE j.title LIKE %:keyword%”)
    List<Job> searchByTitle(@Param(“keyword”) String keyword);
}
```

**Das macht Spring automatisch:**
- ❌ Du schreibst **nicht** die SQL!
- ✅ Spring erzeugt die Queries automatisch
- ✅ Type-safe Zugriff auf Datenbank

---

### 4️⃣ DTO (Data Transfer Object)

**Aufgabe:** Daten zwischen Frontend und Backend transportieren

**Warum nicht direkt die Entity?**

```java
// ❌ FALSCH - Entity direkt zurückgeben
@GetMapping(“/{id}”)
public Job getJob(@PathVariable Long id) {
    return jobRepository.findById(id).orElse(null);
}
// Problem: 
// - Sensitive Daten könnten exposed werden (z.B. interne IDs)
// - Frontend erhält Datenbankstruktur statt API-Struktur
// - Änderungen bei Datenbank beeinflussen API

// ✅ RICHTIG - DTO verwenden
@GetMapping(“/{id}”)
public ResponseEntity<JobDTO> getJob(@PathVariable Long id) {
    Job job = jobRepository.findById(id)
        .orElseThrow(() -> new JobNotFoundException());
    return ResponseEntity.ok(JobDTO.fromEntity(job));
}
```

**DTO Beispiel:**

```java
@Data
public class JobDTO {
    private Long id;
    private String title;
    private String company;
    private JobStatus status;
    private LocalDateTime createdAt;
    
    // Sensitive Daten NICHT hier!
    // private String internalNotes;
    
    // Umwandlung von Entity zu DTO
    public static JobDTO fromEntity(Job job) {
        JobDTO dto = new JobDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setCompany(job.getCompany());
        dto.setStatus(job.getStatus());
        dto.setCreatedAt(job.getCreatedAt());
        return dto;
    }
}
```

---

### 5️⃣ Model / Entity (Datenbankmodell)

**Aufgabe:** Datenstruktur für die Datenbank

```java
@Entity
@Table(name = “jobs”)
@Data
@NoArgsConstructor
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String company;
    
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    
    @Column(name = “created_at”)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
```

---

## 📊 Datenfluss - Ein konkretes Beispiel

**Szenario:** Benutzer erstellt einen neuen Job im Frontend

```
1. FRONTEND
   User klickt “Job erstellen”
   → Sendet: POST /api/jobs
   → Body: { title: “Java Developer”, company: “Tech Corp” }

2. CONTROLLER
   JobController.createJob(CreateJobDTO request) wird aufgerufen
   → Validiert nicht! Leitet einfach weiter
   → Ruft jobService.createJob(request) auf

3. SERVICE
   JobService.createJob(CreateJobDTO request) wird ausgeführt
   → ✅ Validiert: Ist title nicht null/leer?
   → ✅ Setzt Standard-Werte: status = OPEN, createdAt = jetzt
   → ✅ Ruft jobRepository.save(job) auf

4. REPOSITORY
   JobRepository.save(job) wird ausgeführt
   → ✅ Speichert in Datenbank
   → ✅ Gibt gespeicherte Job-Entity zurück

5. SERVICE (Rückweg)
   → Konvertiert Entity zu JobDTO
   → Gibt JobDTO zurück an Controller

6. CONTROLLER (Rückweg)
   → Wrappet DTO in ResponseEntity
   → Status: 201 CREATED
   → Body: { id: 1, title: “Java Developer”, ... }

7. FRONTEND
   → Erhält Antwort mit HTTP 201
   → Neue Job wird angezeigt
   → UI aktualisiert sich
```

---

## ✅ Die Vorteile dieser Architektur

| Vorteil | Beispiel |
|---------|---------|
| **Testbarkeit** | Service kann ohne Datenbank getestet werden (Mock Repository) |
| **Wartbarkeit** | Logik-Änderung? Nur Service ändern, nicht Controller |
| **Reusability** | Service kann von mehreren Controllern verwendet werden |
| **Skalierbarkeit** | Neue Features folgen dem gleichen Pattern |
| **Separation of Concerns** | Jede Schicht hat eine klare Aufgabe |

---

## 🚫 Was ist FALSCH?

```java
// ❌ FALSCH: Controller mit Business-Logik
@RestController
public class JobController {
    @PostMapping(“/jobs”)
    public void createJob(JobDTO request) {
        // Validierung im Controller!
        if (request.getTitle() == null) throw new Exception();
        
        // Datenbankzugriff im Controller!
        if (database.jobExists(request.getTitle())) throw new Exception();
        
        // Geschäftslogik im Controller!
        Job job = new Job();
        job.setTitle(request.getTitle());
        database.save(job);
    }
}

// ✅ RICHTIG: Controller delegiert alles
@RestController
public class JobController {
    private final JobService jobService;
    
    @PostMapping(“/jobs”)
    public ResponseEntity<JobDTO> createJob(@RequestBody CreateJobDTO request) {
        return ResponseEntity.ok(jobService.createJob(request));
    }
}
```

---

## 🔄 Schichten-Kommunikation

```
Controller ──→ Service ──→ Repository ──→ Database
   ↑             ↓            ↓
   └─────────────┴────────────┘
   (Rückgabewerte)
```

**Wichtig:** 
- ✅ Schichten dürfen **nur nach unten** kommunizieren
- ✅ Nur Service darf mehrere Repositories nutzen
- ✅ Controller kennt nur Service, nicht Repository

---

## 💡 Best Practices

1. **Immer DTOs verwenden** für externe APIs
2. **Exceptions in Service werfen** (nicht in Controller)
3. **Service ist geschäftslogik-Zentrum**
4. **Repository ist nur für DB-Queries**
5. **Controller ist nur für HTTP-Handling**

---

## 📖 Weitere Infos

- [SPRING.md](./SPRING.md) - Spring Annotations & Dependency Injection
- GitHub: Spring Boot Best Practices
