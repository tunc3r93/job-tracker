# Architektur

## Überblick

Der Job Tracker ist als klassische Fullstack-Anwendung aufgebaut und besteht aus zwei Hauptteilen:

- Backend (Java + Spring)
- Frontend (React)

Die Anwendung folgt einer **schichtenbasierten Architektur**, um Code übersichtlich, wartbar und erweiterbar zu halten.

---

## Schichtenmodell

Das Backend ist in mehrere logische Schichten unterteilt:

### 1. Controller (API-Schicht)
- Nimmt HTTP-Anfragen entgegen
- Gibt Responses zurück (JSON)
- Enthält **keine Geschäftslogik**

Aufgabe: Kommunikation zwischen Client und Backend

---

### 2. Service (Business-Logik)
- Enthält die eigentliche Logik der Anwendung
- Verarbeitet Daten und entscheidet, *was passieren soll*

Beispiel:
- Job erstellen
- Status ändern
- Validierungen

---

### 3. Repository (Datenzugriff)
- Kommunikation mit der Datenbank
- Nutzt Spring Data / JPA

Aufgabe:
- Speichern
- Laden
- Löschen von Daten

---

### 4. Model / Entity
- Repräsentiert die Datenstruktur (z. B. `Job`)
- Wird in der Datenbank gespeichert

---

## Datenfluss

1. Frontend sendet Anfrage (z. B. "Neuen Job erstellen")
2. Controller empfängt Request
3. Service verarbeitet die Logik
4. Repository speichert Daten
5. Response geht zurück ans Frontend

---

## Warum diese Architektur?

Diese Trennung sorgt für:

- **Bessere Wartbarkeit**
- **Einfacheres Testen**
- **Klare Verantwortlichkeiten**
- **Skalierbarkeit**

Ohne diese Struktur würde schnell „Spaghetti-Code“ entstehen.

---

## Frontend-Architektur

Das React-Frontend ist komponentenbasiert:

- UI wird in kleine, wiederverwendbare Komponenten aufgeteilt
- State wird lokal oder global verwaltet
- Kommunikation mit Backend über HTTP (REST API)

---

## Fazit

Die Architektur orientiert sich an Best Practices moderner Webentwicklung und hilft dabei, strukturiert und professionell zu arbeiten.
