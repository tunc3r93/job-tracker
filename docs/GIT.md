# Git Befehle & Workflow - Job Tracker

Ein praktischer Guide zu wichtigen Git-Befehlen für dieses Projekt, ohne sensitive Daten.

---

## 🔧 Grundlegende Befehle

### Repository initialisieren & klonen
```bash
# Repository von GitHub klonen
git clone https://github.com/tunc3r93/job-tracker.git
cd job-tracker

# Ein neues lokales Repository erstellen
git init
```

### Konfiguration (Lokal)
```bash
# Benutzer konfigurieren (lokal für dieses Projekt)
git config user.name "Dein Name"
git config user.email "deine.email@example.com"

# Konfiguration anschauen
git config --list
git config user.name
git config user.email
```

---

## 📝 Arbeiten mit Änderungen

### Status und Verlauf
```bash
# Aktuellen Status anschauen
git status

# Letzten Commit anschauen
git log -1
git log --oneline -10

# Detaillierter Verlauf mit Autor und Zeit
git log --pretty=format:"%h %an <%ae> - %s"

# Änderungen vor dem Commiten anschauen
git diff
git diff --staged
```

### Änderungen hinzufügen & Committen
```bash
# Alle Änderungen hinzufügen
git add .

# Nur spezifische Datei hinzufügen
git add README.md
git add docs/

# Änderungen committen
git commit -m "feat: Neue Feature beschreibung"
git commit -m "fix: Bug-Fix Beschreibung"
git commit -m "docs: Dokumentation aktualisiert"

# Mit Editor committen (für längere Messages)
git commit

# Letzte Änderungen zum vorherigen Commit hinzufügen
git commit --amend --no-edit
```

### Wichtige Commit-Types
```bash
# Neue Features
git commit -m "feat: Feature-Name hinzugefügt"

# Bug-Fixes
git commit -m "fix: Bug in X behoben"

# Dokumentation
git commit -m "docs: README aktualisiert"

# Tests
git commit -m "test: Unit Tests für X hinzugefügt"

# Refactoring
git commit -m "refactor: Code-Struktur verbessert"

# Style/Formatting
git commit -m "style: Code-Formatierung angepasst"
```

---

## 🌿 Arbeiten mit Branches

### Branch-Verwaltung
```bash
# Alle lokalen Branches anschauen
git branch

# Alle Branches (lokal + remote)
git branch -a

# Neuen Branch erstellen
git branch feature/meine-neue-feature

# Zu Branch wechseln
git checkout feature/meine-neue-feature

# Branch erstellen und direkt wechseln (Shorthand)
git checkout -b feature/meine-neue-feature

# Branch löschen (lokal)
git branch -d feature/meine-neue-feature

# Branch löschen (erzwungen)
git branch -D feature/meine-neue-feature
```

### Branch-Namen Konvention
```
feature/          - Neue Features
bugfix/           - Bug Fixes
docs/             - Dokumentation
refactor/         - Code-Refactoring
chore/            - Wartung und Konfiguration

Beispiele:
- feature/job-posting-api
- bugfix/null-pointer-exception
- docs/testing-guide
- refactor/service-layer
```

---

## 🔄 Push & Pull

### Push zu GitHub
```bash
# Aktuellen Branch zu GitHub pushen
git push origin main

# Neuen Branch zu GitHub pushen
git push origin feature/meine-feature

# Alle Branches pushen
git push origin --all

# Tags pushen
git push origin --tags
```

### Pull von GitHub
```bash
# Letzte Änderungen vom Main-Branch holen
git pull origin main

# Spezifischen Branch holen
git pull origin feature/andere-feature

# Nur herunterladen, ohne zu mergen
git fetch origin
```

### Merge Branches
```bash
# Aktuellen Branch auf main updaten
git checkout main
git pull origin main

# Feature-Branch in main mergen
git merge feature/meine-feature

# Nach erfolgreichem Merge: Branch löschen
git branch -d feature/meine-feature
git push origin --delete feature/meine-feature
```

---

## 🚨 Häufige Probleme & Lösungen

### "Changes not staged for commit"
```bash
# Alle Änderungen anschauen
git status

# Spezifische Änderungen hinzufügen
git add dateiname.java
git commit -m "Beschreibung"
```

### "Untracked files"
```bash
# Neue Dateien hinzufügen
git add .
git commit -m "Neue Dateien hinzugefügt"

# Oder zu .gitignore hinzufügen, wenn nicht verfolgt werden soll
```

### "Commit auf falschen Branch"
```bash
# Letzten Commit rückgängig machen (lokal nur!)
git reset --soft HEAD~1

# Zu korrektem Branch wechseln
git checkout feature/richtig-branch
git commit -m "Message"
```

### "Merge-Konflikt"
```bash
# Konflikt-Status anschauen
git status

# Konflikt-Dateien in Editor öffnen und manuell beheben
# Marker: <<<<<<<, =======, >>>>>>>

# Nach dem Beheben:
git add konflikt-datei.java
git commit -m "Merge-Konflikt behoben"
```

### "Letzte Änderungen verwerfen"
```bash
# Alle Änderungen seit letztem Commit verwerfen
git reset --hard HEAD

# Spezifische Datei auf letzten Stand zurücksetzen
git checkout -- dateiname.java
```

### "Commits anschauen vor Push"
```bash
# Commits zwischen lokalem und remote anschauen
git log origin/main..HEAD

# Detaillierte Änderungen
git log -p origin/main..HEAD
```

---

## 📊 Nützliche Kombinationen

### Typischer Feature-Workflow
```bash
# 1. Feature-Branch erstellen
git checkout -b feature/neue-feature

# 2. Änderungen machen
# ... code ändern ...

# 3. Änderungen hinzufügen und committen
git add .
git commit -m "feat: Neue Feature implementiert"

# 4. Vor Push: Main aktualisieren
git fetch origin
git merge origin/main

# 5. Zu GitHub pushen
git push origin feature/neue-feature

# 6. Pull Request auf GitHub erstellen (manuell)
```

### Lokalen Branch mit Remote synchronisieren
```bash
# Remote-Changes holen
git fetch origin

# Aktuellen Branch mit Remote mergen
git merge origin/main

# Oder direkt pull (fetch + merge)
git pull origin main
```

### Saubere History vor Push
```bash
# Letzten Commit anschauen
git log -1

# Falls Fehler: Commit ändern
git commit --amend -m "Neue Nachricht"

# Falls mehrere Commits: Interactive Rebase
git rebase -i HEAD~3
```

---

## 🔐 Sicherheit & Best Practices

### ✅ DO's
- ✅ Aussagekräftige Commit-Messages schreiben
- ✅ Regelmäßig committen (kleine, logische Chunks)
- ✅ Vor Push: `git log origin/main..HEAD` prüfen
- ✅ Branches für neue Features verwenden
- ✅ `.gitignore` beachten (sensitive Dateien nicht committen)

### ❌ DON'Ts
- ❌ Passwörter, API-Keys oder Tokens committen
- ❌ Ungetesteten Code zu main pushen
- ❌ Große binäre Dateien committen
- ❌ `git push --force` ohne guten Grund
- ❌ Sensible Daten in Commit-Messages
- ❌ Direkt zu main pushen (außer Hotfixes)

### Sensitive Daten schützen
```bash
# Diese Dateien ignorieren (bereits in .gitignore):
# - .env
# - application-prod.properties
# - *.key
# - *.pem
# - password.txt

# Prüfen, was gepusht wird
git diff origin/main
git show HEAD

# Falls versehentlich committed:
git reset --soft HEAD~1
# ... sensitive Dateien entfernen ...
git commit -m "Neue Nachricht"
```

---

## 📚 Weitere Ressourcen

- [Pro Git Book](https://git-scm.com/book/en/v2)
- [GitHub Guides](https://guides.github.com/)
- [Atlassian Git Tutorials](https://www.atlassian.com/git/tutorials)
- [Git Cheat Sheet](https://git-scm.com/docs)

---

**Happy Coding! 🚀**
