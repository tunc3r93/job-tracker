# Job Tracker 📋

Ein **Full-Stack Learning-Projekt** zur Verfolgung von Bewerbungen und Job-Anwendungen. Dieses Projekt demonstriert professionelle Softwareentwicklung mit modernen Technologien, Clean Architecture und umfassenden Tests.

---

## 🎯 Projektüberblick

**Job Tracker** hilft dabei, den Überblick über deine Bewerbungen zu behalten und zeigt Best Practices in der modernen Software-Entwicklung:

- ✅ **Neue Jobs hinzufügen** → Vollständiges Tracking über alle Phasen
- ✅ **Statuses aktualisieren** → Von Bewerbung bis Angebot oder Absage
- ✅ **Übersichtliche UI** → Intuitive Verwaltung deines Job-Hunting-Prozesses
- ✅ **REST API** → Backend mit allen CRUD-Operationen
- ✅ **Unit Tests** → Umfassende Test-Coverage für Controller & Services
- ✅ **Clean Architecture** → Layered Architecture mit Separation of Concerns
- ✅ **Spring Boot Best Practices** → Dependency Injection, Configuration Management

---

## 🛠️ Tech Stack

### Backend
- **Java 21** — Neueste LTS-Version mit modernen Features
- **Spring Boot 4.0.5** — Framework für produktionsreife REST APIs
- **Spring Data JPA** — ORM für Datenbankzugriff
- **PostgreSQL / MySQL** — Relationale Datenbanken (Docker-Support verfügbar)
- **Lombok** — Boilerplate-Code-Reduktion
- **JUnit 5** — Modernes Unit-Testing Framework
- **Mockito** — Mocking-Framework für isolierte Tests

### Build & Tools
- **Maven** — Dependency Management und Build-Automation
- **JaCoCo** — Code Coverage Reports
- **Git** — Version Control

---

## 📁 Projektstruktur

```
job-tracker/
├── backend/
│   └── src/
│       ├── main/java/com/jobtracker/backend/
│       │   ├── controller/          ← REST API Endpoints
│       │   ├── service/             ← Business Logic
│       │   ├── repository/          ← Database Access (JPA)
│       │   ├── model/               ← Entity-Klassen
│       │   ├── dto/                 ← Data Transfer Objects
│       │   ├── exception/           ← Custom Exceptions
│       │   ├── config/              ← Spring Configuration
│       │   └── util/                ← Utility Functions
│       │
│       └── test/java/com/jobtracker/backend/
│           ├── controller/          ← Controller Tests
│           ├── service/             ← Service Tests
│           └── repository/          ← Repository Tests
│
├── docs/
│   ├── README.md                    ← Dieses File
│   ├── ARCHITECTURE.md              ← Architektur-Dokumentation
│   ├── SPRING.md                    ← Spring Boot Guide
│   └── TESTING.md                   ← Unit Testing Guide
│
├── pom.xml                          ← Maven Configuration
└── .gitignore

```

---

## 🚀 Getting Started

### Voraussetzungen
- **Java 21** installiert: [Download](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven 3.8+** installiert: [Download](https://maven.apache.org/download.cgi)
- **Git** installiert
- **PostgreSQL** oder **MySQL** (optional für lokale Entwicklung)

### Installation

1. **Repository klonen:**
```bash
git clone https://github.com/tunc3r93/job-tracker.git
cd job-tracker
```

2. **Dependencies installieren:**
```bash
mvn clean install
```

3. **Anwendung starten:**
```bash
mvn spring-boot:run
```

Die API ist dann verfügbar unter: `http://localhost:8080`

---

## 📚 Dokumentation

Detaillierte Dokumentation findest du in diesen Dateien:

- **[ARCHITECTURE.md](./docs/ARCHITECTURE.md)** — Layered Architecture, Datenfluss, Best Practices
- **[SPRING.md](./docs/SPRING.md)** — Spring Boot Fundamentals, Dependency Injection, Annotations
- **[TESTING.md](./docs/TESTING.md)** — Unit Testing Guide, Test-Struktur, Ausführung

---

## 🧪 Tests ausführen

### Alle Tests ausführen
```bash
mvn test
```

### Spezifische Test-Klasse
```bash
mvn test -Dtest=JobPostingControllerTest
```

### Mit Code Coverage Report
```bash
mvn test jacoco:report
# Report öffnet sich in: target/site/jacoco/index.html
```

---

## 📖 API-Dokumentation

### Endpoints

| Method | Endpoint | Beschreibung |
|--------|----------|-------------|
| GET | `/jobs` | Alle Jobs abrufen |
| GET | `/jobs/{id}` | Job nach ID abrufen |
| POST | `/jobs` | Neuen Job erstellen |
| PUT | `/jobs/{id}` | Job aktualisieren |
| DELETE | `/jobs/{id}` | Job löschen |

### Beispiel: Job erstellen

```bash
curl -X POST http://localhost:8080/jobs \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Senior Java Developer",
    "company": "Tech Corp",
    "description": "Exciting backend development role"
  }'
```

---

## 🎓 Lernziele

Dieses Projekt zeigt wie man:

1. **Clean Architecture anwendet** — Separation of Concerns mit Layered Architecture
2. **Spring Boot richtig nutzt** — Dependency Injection, Annotations, Configuration
3. **Unit Tests schreibt** — AAA-Pattern, Mocking, Test-Coverage
4. **REST APIs designt** — RESTful Principles, HTTP-Verben, Status-Codes
5. **Git professionell einsetzt** — Commits, Branches, Collaboration

---

## 🔄 Development Workflow

1. **Feature-Branch erstellen:**
   ```bash
   git checkout -b feature/my-feature
   ```

2. **Code schreiben und testen:**
   ```bash
   mvn test
   ```

3. **Änderungen committen:**
   ```bash
   git add .
   git commit -m "feat: Beschreibung der Änderung"
   ```

4. **Push und Pull Request:**
   ```bash
   git push origin feature/my-feature
   ```

---

## 📝 Lizenz

Dieses Projekt ist lizenziert unter der MIT License. Siehe [LICENSE](LICENSE) für Details.

---

## 👤 Autor

**Tuncer Arici**
- GitHub: [@tunc3r93](https://github.com/tunc3r93)
- Email: blizgamez93@hotmail.de

---

## 🤝 Contribution

Contributions sind willkommen! Bitte erstelle einen Fork, mache deine Änderungen und öffne einen Pull Request.

### Code Standards
- Java Code folgt [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- Minimum 80% Test Coverage für neue Features
- Aussagekräftige Commit-Messages auf Deutsch oder Englisch

---

**Viel Spaß beim Lernen! 🚀**
