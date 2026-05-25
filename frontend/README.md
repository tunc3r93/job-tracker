# Job Tracker Frontend 🎨

React Frontend für Job Tracker mit **TypeScript**, **Hooks** und **Context API**.

---

## 📋 Übersicht

Dieses Frontend bietet:

- ✅ **Flexible Navbar** → Position konfigurierbar (top, bottom, left, right)
- ✅ **TypeScript** → Type-safe React-Komponenten
- ✅ **Context API** → Global State Management ohne zusätzliche Libraries
- ✅ **Hooks** → Moderne React-Entwicklung
- ✅ **Datenbank-Sync** → Navbar-Position wird in der DB gespeichert

---

## 🛠️ Tech Stack

- **React 18** — Modern UI Library
- **TypeScript 5** — Type-safe JavaScript
- **Context API** — Global State Management
- **CSS3** — Modern Styling mit Responsive Design

---

## 📁 Projektstruktur

```
frontend/
├── public/
│   └── index.html              ← HTML Entry Point
├── src/
│   ├── components/
│   │   └── Navbar.tsx          ← Navbar Komponente
│   ├── context/
│   │   └── NavbarContext.tsx   ← Global State Management
│   ├── hooks/                  ← Custom React Hooks
│   ├── pages/                  ← Page-Komponenten
│   ├── services/               ← API-Kommunikation
│   ├── styles/
│   │   └── Navbar.css          ← Navbar Styling
│   ├── types/
│   │   └── index.ts            ← TypeScript Typen
│   ├── App.tsx                 ← Root-Komponente
│   ├── App.css                 ← App Styling
│   └── index.tsx               ← React Entry Point
├── package.json
└── tsconfig.json
```

---

## 🚀 Installation & Start

```bash
# In frontend/ Verzeichnis navigieren
cd frontend

# Dependencies installieren
npm install

# Development Server starten (Port 3000)
npm start

# Build für Production
npm build

# Tests ausführen
npm test
```

---

## 🏗️ Architektur

### Komponenten-Struktur

```
App (Root)
│
├── NavbarProvider (Context)
│   │
│   └── AppContent
│       ├── Navbar
│       │   ├── Logo
│       │   └── Menu
│       │
│       └── main (Content)
```

### State Flow

```
NavbarContext (Global State)
    ↓
    ├─ currentUser: User
    ├─ navbarPosition: NavbarPosition
    ├─ setNavbarPosition: (position) => void
    ├─ isLoading: boolean
    └─ error: string | null
        ↓
    useNavbar() Hook
        ↓
    Komponenten verwenden State
```

---

## 🎯 NavbarPosition enum

Die Navbar kann 4 Positionen einnehmen:

| Position | Beschreibung | Effekt |
|----------|-------------|--------|
| **TOP** | Oben | Horizontale Navbar, Content verschoben nach unten |
| **BOTTOM** | Unten | Horizontale Navbar, Content verschoben nach oben |
| **LEFT** | Links | Vertikale Navbar, Content nach rechts |
| **RIGHT** | Rechts | Vertikale Navbar, Content nach links |

---

## 📝 Komponenten Guide

### Navbar Component

```tsx
import { Navbar } from './components/Navbar';
import { NavbarPosition } from './types';

// In einer Komponente:
<Navbar position={NavbarPosition.TOP} />
```

Die Navbar passt sich automatisch an die Position an:
- **TOP/BOTTOM**: Horizontales Layout
- **LEFT/RIGHT**: Vertikales Layout

---

### useNavbar Hook

```tsx
import { useNavbar } from './context/NavbarContext';

function MyComponent() {
  const { navbarPosition, setNavbarPosition } = useNavbar();

  return (
    <>
      <p>Aktuelle Position: {navbarPosition}</p>
      <button onClick={() => setNavbarPosition(NavbarPosition.TOP)}>
        Nach oben verschieben
      </button>
    </>
  );
}
```

---

## 🔌 Backend Integration

### API Calls (TODO)

Die API-Integration erfolgt in `NavbarContext.tsx`:

```typescript
// Benutzer laden
const response = await fetch(`/api/users/${userId}`);

// Navbar-Position speichern
await fetch(`/api/users/${userId}/navbar-position`, {
  method: 'PUT',
  body: JSON.stringify({ navbarPosition: position })
});
```

**Backend Endpoints (geplant):**
- `GET /api/users/{id}` → Benutzer + Einstellungen laden
- `PUT /api/users/{id}/navbar-position` → Position speichern

---

## 🎨 Styling

Das Frontend nutzt **CSS3** mit:
- Flexbox für Layouts
- CSS Transitions für sanfte Änderungen
- Mobile-First Responsive Design
- CSS-Variablen für Farben (ausbaubar)

**Beispiel - Responsive Design:**
```css
@media (max-width: 768px) {
  /* Mobile Styles */
}
```

---

## 📖 Weitere Ressourcen

- [ARCHITECTURE.md](../docs/REACT-ARCHITECTURE.md) — Detaillierte Frontend-Architektur
- [TESTING.md](../docs/TESTING.md) — Testing Strategien
- [Backend-Docs](../backend/README.md) — Backend Dokumentation

---

## 🧠 Lernressourcen

Dieses Projekt zeigt:
- ✅ Context API für State Management
- ✅ TypeScript in React
- ✅ Hooks (useState, useEffect, useContext)
- ✅ CSS3 Layouts (Flexbox, Grid)
- ✅ Responsive Web Design
- ✅ Component Composition

---

## 📞 Support

Fragen? Schau in die [Dokumentation](../docs/) oder erstelle einen Issue! 🚀
