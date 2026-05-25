# Job Tracker 📋

Ein **vollständiges Full-Stack Learning-Projekt** mit modernem Frontend und Backend. Demonstriert professionelle Softwareentwicklung mit React 18, Spring Boot, TypeScript und umfassenden Tests.

---

## 🎯 Projektüberblick

**Job Tracker** ist ein durchdachtes Full-Stack-System mit:

### Frontend (React)
- ✅ **React 18 mit TypeScript** — Moderne, typsichere Frontend-Entwicklung
- ✅ **Navbar Position Management** — Flexible Navigation (TOP, BOTTOM, LEFT, RIGHT)
- ✅ **NavbarSettings Component** — Live-Preview und Positionsänderung
- ✅ **Context API** — Globale State Management ohne Redux
- ✅ **Custom Hooks** — Wiederverwendbare Logik (useFetch, useLocalStorage)
- ✅ **Responsive Design** — Mobile-first CSS mit BEM Pattern
- ✅ **API Integration** — Service Layer für saubere API-Aufrufe

### Backend (Spring Boot)
- ✅ **User Management** — Benutzerverwaltung mit Preference-Speicherung
- ✅ **REST APIs** — Modern designed Endpoints (POST/GET/PUT)
- ✅ **JPA Entities** — User mit Navbar-Position-Enum
- ✅ **Service Layer** — Business Logic mit @Transactional
- ✅ **DTOs** — Data Transfer Objects für API-Kommunikation
- ✅ **Unit & Integration Tests** — 12 Tests mit Mockito & MockMvc
- ✅ **Clean Architecture** — Layered Architecture mit Separation of Concerns

---

## 🛠️ Tech Stack

### Frontend
- **React 18** — Moderne UI-Library mit Hooks
- **TypeScript** — Type-safe JavaScript (strict mode)
- **Context API** — Globale State Management
- **CSS3** — Responsive Design (BEM Pattern, Flexbox)
- **Fetch API** — Native HTTP-Requests
- **Custom Hooks** — useFetch, useLocalStorage, useNavbar

### Backend
- **Java 21** — Neueste LTS-Version mit modernen Features
- **Spring Boot 4.0.5** — Framework für produktionsreife REST APIs
- **Spring Data JPA** — ORM für Datenbankzugriff
- **PostgreSQL / MySQL** — Relationale Datenbanken
- **Lombok** — Boilerplate-Code-Reduktion
- **JUnit 5** — Modernes Unit-Testing Framework
- **Mockito** — Mocking-Framework für isolierte Tests
- **MockMvc** — Spring Boot Integration Testing

### Build & Tools
- **Maven** — Dependency Management und Build-Automation
- **npm** — Frontend Package Management
- **JaCoCo** — Code Coverage Reports
- **Git** — Version Control mit Conventional Commits

---

## 📁 Projektstruktur

```
job-tracker/
│
├── frontend/                        ← React Frontend
│   ├── public/
│   │   └── index.html
│   ├── src/
│   │   ├── components/              ← React Components
│   │   │   ├── Navbar.tsx          ← Main Navigation
│   │   │   └── NavbarSettings.tsx  ← Settings Panel
│   │   ├── context/                 ← Context API
│   │   │   └── NavbarContext.tsx
│   │   ├── hooks/                   ← Custom Hooks
│   │   │   ├── useFetch.ts
│   │   │   └── useLocalStorage.ts
│   │   ├── pages/                   ← Page Components
│   │   │   ├── HomePage.tsx
│   │   │   └── SettingsPage.tsx
│   │   ├── services/                ← API Services
│   │   │   └── api.ts
│   │   ├── styles/                  ← CSS Files (BEM)
│   │   ├── types/                   ← TypeScript Definitions
│   │   ├── App.tsx
│   │   └── index.tsx
│   ├── package.json
│   ├── tsconfig.json
│   └── README.md
│
├── backend/                         ← Spring Boot Backend
│   ├── src/
│   │   ├── main/java/com/jobtracker/backend/
│   │   │   ├── controller/          ← REST API Endpoints
│   │   │   ├── service/             ← Business Logic
│   │   │   ├── repository/          ← Database Access (JPA)
│   │   │   ├── model/               ← Entity-Klassen & Enums
│   │   │   ├── dto/                 ← Data Transfer Objects
│   │   │   └── config/              ← Spring Configuration
│   │   │
│   │   └── test/java/com/jobtracker/backend/
│   │       ├── controller/          ← Integration Tests
│   │       └── service/             ← Unit Tests
│   │
│   └── pom.xml                      ← Maven Configuration
│
├── docs/                            ← Dokumentation
│   ├── REACT-ARCHITECTURE.md        ← Frontend Architecture
│   ├── REACT-LEARNING.md            ← React & TypeScript Guide
│   ├── BACKEND-APIS.md              ← API Documentation
│   └── README.md                    ← Documentation Index
│
├── README.md                        ← Dieses File
├── FRONTEND-SETUP.md                ← Frontend Setup Guide
└── .gitignore
```

**Statistiken:**
- Frontend: 25 Dateien (~1500 LOC)
- Backend: 10 Dateien (~800 LOC)
- Tests: 12 Tests (unit + integration)
- Dokumentation: 8 Dateien (~2000 LOC)
- **Total: 45+ Dateien, ~4700 LOC**

---

## 🚀 Getting Started

### Voraussetzungen
- **Java 21** installiert: [Download](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven 3.8+** installiert: [Download](https://maven.apache.org/download.cgi)
- **Node.js 18+** installiert: [Download](https://nodejs.org/)
- **Git** installiert
- **PostgreSQL** oder **MySQL** (optional)

### Installation & Setup

1. **Repository klonen:**
```bash
git clone https://github.com/tunc3r93/job-tracker.git
cd job-tracker
```

2. **Backend Setup:**
```bash
cd backend
mvn clean install
```

3. **Frontend Setup:**
```bash
cd ../frontend
npm install
```

### Anwendung starten

**Terminal 1 - Backend:**
```bash
cd backend
mvn spring-boot:run
```
Backend läuft unter: `http://localhost:8080`

**Terminal 2 - Frontend:**
```bash
cd frontend
npm start
```
Frontend läuft unter: `http://localhost:3000`

Öffne [http://localhost:3000](http://localhost:3000) im Browser!

---

## 📚 Dokumentation

Detaillierte Dokumentation findest du in diesen Dateien:

### Frontend
- **[REACT-ARCHITECTURE.md](./docs/REACT-ARCHITECTURE.md)** — Component Structure, State Management, Hooks
- **[REACT-LEARNING.md](./docs/REACT-LEARNING.md)** — React & TypeScript Fundamentals (1000+ Zeilen)
- **[FRONTEND-SETUP.md](./FRONTEND-SETUP.md)** — Frontend Project Overview

### Backend
- **[BACKEND-APIS.md](./docs/BACKEND-APIS.md)** — REST API Documentation mit cURL Examples
- **[docs/README.md](./docs/README.md)** — Documentation Index

### Testing
- **[TESTING.md](./docs/TESTING.md)** — Unit Testing Guide, Test-Struktur, Coverage Reports

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

### User Management Endpoints

| Method | Endpoint | Beschreibung | Status |
|--------|----------|-------------|--------|
| POST | `/api/users` | Benutzer erstellen | 201 Created |
| GET | `/api/users/{id}` | Benutzer abrufen | 200 OK |
| GET | `/api/users` | Alle Benutzer abrufen | 200 OK |
| PUT | `/api/users/{id}/navbar-position` | Navbar-Position aktualisieren | 200 OK |

### Beispiele

**Benutzer erstellen:**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "name": "John Doe"
  }'
```

**Navbar-Position aktualisieren:**
```bash
curl -X PUT http://localhost:8080/api/users/1/navbar-position \
  -H "Content-Type: application/json" \
  -d '{
    "position": "LEFT"
  }'
```

**Benutzer abrufen:**
```bash
curl http://localhost:8080/api/users/1
```

Detaillierte API-Dokumentation: [BACKEND-APIS.md](./docs/BACKEND-APIS.md)

---

## 🎓 Lernziele

Dieses Projekt demonstriert professionelle Entwicklung in allen Bereichen:

### Frontend
1. **React 18 mit TypeScript** — Komponenten, Hooks, Type Safety
2. **State Management** — Context API statt Redux
3. **Custom Hooks** — Wiederverwendbare Logik (useFetch, useLocalStorage)
4. **API Integration** — Service Layer Pattern für saubere Code-Organisation
5. **Responsive Design** — CSS3, BEM Pattern, Mobile-first
6. **Component Composition** — Smart/Dumb Components, Props Management

### Backend
1. **Clean Architecture** — Layered Architecture mit Separation of Concerns
2. **Spring Boot Best Practices** — Dependency Injection, Annotations, Configuration
3. **REST API Design** — RESTful Principles, HTTP-Verben, Status-Codes, DTOs
4. **Unit & Integration Tests** — Mockito, MockMvc, AAA-Pattern
5. **JPA/Hibernate** — Entity Lifecycle, Relationships, Repositories
6. **Transactional Consistency** — @Transactional, Data Integrity

### Allgemein
1. **Full-Stack Development** — Frontend & Backend zusammenbringen
2. **Git & Version Control** — Conventional Commits, Branches, Collaboration
3. **Professional Coding** — Code Organization, Documentation, Tests
4. **Learning-Oriented** — Code ist kommentiert und dokumentiert zum Lernen

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

---

## 🤝 Contribution

Contributions sind willkommen! Bitte erstelle einen Fork, mache deine Änderungen und öffne einen Pull Request.

### Code Standards
- Java Code folgt [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- Minimum 80% Test Coverage für neue Features
- Aussagekräftige Commit-Messages auf Deutsch oder Englisch

---
