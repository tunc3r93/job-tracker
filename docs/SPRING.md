# Spring Boot Essentials 🚀

## Was ist Spring?

**Spring** ist ein Framework, das Java-Entwicklung massiv vereinfacht. Es kümmert sich um:
- 🔧 Objektverwaltung (IoC Container)
- 🔌 Abhängigkeitsauflösung (Dependency Injection)
- 🛡️ Cross-Cutting Concerns (Transaktionen, Security, Logging)

**Spring Boot** baut darauf auf und bietet:
- ⚡ Zero-Configuration Setup
- 📦 Eingebaute Server (kein Tomcat-Download nötig)
- 🎯 Convention over Configuration (weniger Code)

---

## 🤔 Warum Spring?

| Punkt | Vorteil |
|-------|---------|
| **Industrie-Standard** | Verwendet von Amazon, Google, Netflix, etc. |
| **Große Community** | Viele Lösungen, Tutorials, Frameworks |
| **Mächtig** | Alles, was du brauchst, ist eingebaut |
| **Wartbar** | Best Practices sind eingebaut |

---

## 🎯 Dependency Injection (DI) - Das Herzstück!

### Das Problem ohne DI

```java
// ❌ FALSCH - Manuelle Objektverwaltung
@Service
public class JobService {
    private JobRepository repo = new JobRepository(); // Gekoppelt!
    
    public void createJob(Job job) {
        repo.save(job);
    }
}

// Problem:
// - Service hängt direkt von JobRepository ab
// - Schwer zu testen (kann nicht gemockt werden)
// - Änderung an Repository = Änderung im Service
```

### Die Lösung mit DI

```java
// ✅ RICHTIG - Spring injiziert Abhängigkeiten
@Service
public class JobService {
    private final JobRepository repo;
    
    // Constructor Injection (empfohlen!)
    public JobService(JobRepository repo) {
        this.repo = repo;
    }
    
    public void createJob(Job job) {
        repo.save(job);
    }
}

// Vorteile:
// ✅ Locker gekoppelt - repo kann mock sein
// ✅ Einfach zu testen
// ✅ Spring erstellt Instanzen automatisch
```

### Wie DI funktioniert

```
1. Spring startet
2. Scannt alle Klassen
3. Findet @Service, @Repository, @Controller
4. Erstellt Instanzen
5. Injiziert überall wo nötig
6. Deine Klasse hat alles was sie braucht
```

---

## 🏷️ Wichtigste Annotationen

### 1. @Component (Generisch)
```java
@Component
public class MyHelper {
    // Spring verwaltet diese Klasse
}
```
**Wann:** Für allgemeine Utility-Klassen

---

### 2. @Service (für Business-Logik)
```java
@Service
public class JobService {
    private final JobRepository jobRepository;
    
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    
    public void createJob(CreateJobDTO request) {
        // Business-Logik hier
        Job job = new Job();
        job.setTitle(request.getTitle());
        jobRepository.save(job);
    }
}
```
**Wann:** Services mit Business-Logik  
**Vorteile:** 
- Spring verwaltet den Lebenszyklus
- Kann in anderen @Services injiziert werden
- Stereotype-Annotation für bessere Lesbarkeit

---

### 3. @Repository (für Datenzugriff)
```java
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatus(JobStatus status);
    
    @Query("SELECT j FROM Job j WHERE j.company = :company")
    List<Job> findByCompany(@Param("company") String company);
}
```
**Wann:** Datenzugriff, JPA Repositories  
**Vorteile:**
- Spring Data JPA generiert Queries automatisch
- Exception Translation
- Type-safe Zugriff

---

### 4. @RestController (für APIs)
```java
@RestController
@RequestMapping("/api/jobs")
public class JobController {
    
    private final JobService jobService;
    
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    
    @GetMapping
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }
    
    @PostMapping
    public ResponseEntity<JobDTO> createJob(
        @RequestBody CreateJobDTO request) {
        return ResponseEntity.ok(jobService.createJob(request));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
```
**Wann:** REST API Endpoints  
**Vorteile:**
- `@GetMapping`, `@PostMapping`, etc. kombiniert @RequestMapping + @ResponseBody
- Automatische JSON-Serialisierung/Deserialisierung
- Spring handhabt HTTP-Status-Codes

---

### 5. @Entity (für Datenbank)
```java
@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String title;
    
    @Column(nullable = false)
    private String company;
    
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
    // Relationships
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Application> applications = new ArrayList<>();
}
```
**Wann:** Datenbank-Objekte (persistent)  
**Wichtige Sub-Annotationen:**
- `@Id` - Primärschlüssel
- `@GeneratedValue` - Auto-Increment
- `@Column` - Spalten-Eigenschaften
- `@OneToMany`, `@ManyToOne` - Beziehungen

---

### 6. @RequestMapping & HTTP-Methoden
```java
@RestController
@RequestMapping("/api/jobs")
public class JobController {
    
    // GET /api/jobs
    @GetMapping
    public List<JobDTO> list() { }
    
    // GET /api/jobs/{id}
    @GetMapping("/{id}")
    public JobDTO getById(@PathVariable Long id) { }
    
    // POST /api/jobs
    @PostMapping
    public ResponseEntity<JobDTO> create(@RequestBody CreateJobDTO req) { }
    
    // PUT /api/jobs/{id}
    @PutMapping("/{id}")
    public JobDTO update(@PathVariable Long id, @RequestBody UpdateJobDTO req) { }
    
    // DELETE /api/jobs/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { }
}
```

---

## 🔌 Dependency Injection - Praktische Beispiele

### Beispiel 1: Service in Service injizieren

```java
@Service
public class ApplicationService {
    
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final EmailService emailService;
    
    // Constructor Injection
    public ApplicationService(
        JobRepository jobRepository,
        ApplicationRepository applicationRepository,
        EmailService emailService) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
        this.emailService = emailService;
    }
    
    public void applyToJob(Long jobId, String userName) {
        Job job = jobRepository.findById(jobId)
            .orElseThrow(() -> new JobNotFoundException());
        
        Application app = new Application();
        app.setJob(job);
        app.setUserName(userName);
        
        applicationRepository.save(app);
        
        // Email service automatisch verfügbar
        emailService.sendConfirmation(userName, job.getTitle());
    }
}
```

### Beispiel 2: Repository in Controller

```java
@RestController
@RequestMapping("/api/jobs")
public class JobController {
    
    private final JobService jobService;
    
    // Spring injiziert JobService automatisch
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    
    @GetMapping
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }
}
```

---

## ⚙️ Application Properties (application.properties oder application.yml)

```properties
# Server
server.port=8080
server.servlet.context-path=/

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/job_tracker
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Logging
logging.level.root=INFO
logging.level.com.jobtracker=DEBUG
```

---

## 🧪 Testen mit Spring

```java
@SpringBootTest
class JobServiceTest {
    
    @Mock
    private JobRepository jobRepository;
    
    @InjectMocks
    private JobService jobService;
    
    @Test
    void testCreateJob() {
        // Arrange
        CreateJobDTO request = new CreateJobDTO();
        request.setTitle("Java Dev");
        
        Job savedJob = new Job();
        savedJob.setId(1L);
        savedJob.setTitle("Java Dev");
        
        when(jobRepository.save(any())).thenReturn(savedJob);
        
        // Act
        JobDTO result = jobService.createJob(request);
        
        // Assert
        assertEquals("Java Dev", result.getTitle());
        verify(jobRepository, times(1)).save(any());
    }
}
```

---

## 🚀 Lifecycle einer Spring Boot Application

```
1. JVM startet
2. Spring Boot Application startet
3. Spring Container wird initialisiert
4. Component Scan - findet alle @Component, @Service, @Repository
5. Bean Creation - erstellt Instanzen
6. Dependency Injection - verbindet die Beans
7. @PostConstruct Methoden werden aufgerufen
8. Tomcat Server startet auf Port 8080
9. Application läuft und nimmt Requests entgegen

Bei Shutdown:
10. @PreDestroy Methoden werden aufgerufen
11. Spring Container wird heruntergefahren
```

---

## 💡 Best Practices

✅ **DO:**
- Constructor Injection verwenden
- `final` für injizierte Felder verwenden
- Services für Business-Logik verwenden
- Properties für Konfiguration verwenden

❌ **DON'T:**
- Field Injection (`@Autowired` auf Feldern) verwenden
- Business-Logik in Controllern schreiben
- Hardcoded Werte in Code

---

## 📚 Weitere Ressourcen

- [Official Spring Documentation](https://spring.io/projects/spring-boot)
- [ARCHITECTURE.md](./ARCHITECTURE.md) - Schichtenmodell
- YouTube: "Spring Boot in 100 Seconds"
