# Unit Testing Guide - Job Tracker

Ein umfassender Guide zu Unit Tests in diesem Spring Boot Projekt.

## Überblick

Unit Tests überprüfen, ob einzelne Komponenten (Units) deines Codes korrekt funktionieren.

### Warum Unit Tests wichtig sind:

- Bugs früh erkennen
- Refactoring mit Sicherheit durchführen
- Automatische Dokumentation des Verhaltens
- CI/CD Integration möglich

## Ordnerstruktur

```text
backend/src/
├── main/java/com/jobtracker/backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   └── model/
│
└── test/java/com/jobtracker/backend/
    ├── controller/
    │   └── JobPostingControllerTest.java
    ├── service/
    │   └── JobPostingServiceTest.java
    └── repository/
        └── JobPostingRepositoryTest.java
```

**Wichtige Regel:** Test-Klasse = Source-Klasse + Test

## Test Types

### 1. Unit Tests (Controller Tests)
- Testen: REST API Endpoints
- Benutzen: @WebMvcTest, MockMvc
- Isolierung: Repository wird gemockt

### 2. Service Tests (Business Logic Tests)
- Testen: Business-Logik
- Benutzen: @ExtendWith(MockitoExtension.class)
- Isolierung: Repository wird gemockt

### 3. Repository Tests (Data Access Tests)
- Testen: Datenbankzugriff
- Benutzen: @DataJpaTest
- Isolierung: In-Memory H2 Datenbank

### 4. Integration Tests
- Testen: Mehrere Schichten zusammen
- Benutzen: @SpringBootTest
- Isolierung: Minimal (echte Komponenten)

## Tests ausführen

### Alle Tests
\\\ash
mvn test
\\\

### Spezifische Test-Klasse
\\\bash
mvn test -Dtest=JobPostingControllerTest
\\\

### Mit Coverage Report
\\\bash
mvn test jacoco:report
start target/site/jacoco/index.html
\\\

## Best Practices

1. **Test-Namen sind Dokumentation**
   - ✅ GUT: testGetAllJobsReturnsEmptyList()
   - ❌ SCHLECHT: test1()

2. **AAA-Pattern verwenden**
   - ARRANGE (Setup)
   - ACT (Execution)
   - ASSERT (Verification)

3. **Mocks vs. Real Objects**
   - @MockBean für externe Dependencies
   - @InjectMocks für die Klasse die du testest

4. **Descriptive Assertions**
   - assertEquals("Java Developer", job.getTitle(), "Job title sollte Java Developer sein")

5. **Test-Daten Setup**
   - Zentraler Setup mit @BeforeEach
   - Verhindere Duplicates

## Beispiele

### Controller Test Beispiel
\\\java
@WebMvcTest(JobPostingController.class)
class JobPostingControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private JobPostingRepository repository;

    @Test
    void testGetAllJobs() throws Exception {
        when(repository.findAll()).thenReturn(List.of(testJob));
        
        mockMvc.perform(get("/jobs"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Java Developer"));
    }
}
\\\

### Service Test Beispiel
\\\java
@ExtendWith(MockitoExtension.class)
class JobPostingServiceTest {
    @Mock private JobPostingRepository repository;
    @InjectMocks private JobPostingService service;

    @Test
    void testCreateJob() {
        when(repository.save(any())).thenReturn(testJob);
        
        JobPosting result = service.createJob(newJob);
        
        assertNotNull(result);
        verify(repository, times(1)).save(any());
    }
}
\\\

## Checkliste für neue Tests

- [ ] Test-Klasse im richtigen Ordner erstellt
- [ ] @DisplayName hinzugefügt
- [ ] @BeforeEach Setup erstellt
- [ ] AAA-Pattern verwendet
- [ ] Mindestens 3 Test-Methoden
- [ ] Happy Path, Error Case, Edge Case abgedeckt
- [ ] Mock/Stub richtig konfiguriert
- [ ] Tests lokal mit 'mvn test' ausgeführt
- [ ] Alle Tests grün ✅

## Weitere Ressourcen

- Spring Boot Testing: https://spring.io/guides/gs/testing-web/
- JUnit 5 Dokumentation: https://junit.org/junit5/
- Mockito: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html

**Happy Testing! 🧪✨**
