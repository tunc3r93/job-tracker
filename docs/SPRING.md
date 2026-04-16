
# Spring Grundlagen

## Was ist Spring?

Spring ist ein Framework für Java, das die Entwicklung von Anwendungen vereinfacht.

Spring Boot macht den Einstieg einfacher:

- Automatische Konfiguration
- Weniger Setup
- Schneller Start

Warum Spring?
- Industriestandard
- Sehr mächtig
- Große Community
- Viele Tools integriert

## Fazit

Spring hilft dabei, strukturierte und skalierbare Anwendungen zu entwickeln, ohne sich um viele technische Details kümmern zu müssen.  
---

## Dependency Injection (DI)

Statt Objekte selbst zu erstellen, übernimmt Spring das:

Java Beispiel:

@Service
class JobService {
    private final JobRepository repo;

    public JobService(JobRepository repo) {
        this.repo = repo;
    }
}

##Wichtige Annotationen

@RestController
- Markiert eine Klasse als API-Controller
  
@Service
- Business-Logik

@Repository
- Datenzugriff

@Entity
- Datenbank-Objekt
