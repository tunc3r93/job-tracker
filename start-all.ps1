# ============================================================================
# 🚀 JOB TRACKER - Starte alles automatisch!
# ============================================================================

Write-Host "🚀 Starte Job Tracker (Frontend + Backend + PostgreSQL)..." -ForegroundColor Yellow
Write-Host ""

# ============================================================================
# STEP 1: PostgreSQL mit Docker starten
# ============================================================================
Write-Host "📦 Step 1: Starte PostgreSQL mit Docker..." -ForegroundColor Cyan

$container = docker ps --filter "name=jobtracker-db" --format "{{.Names}}" 2>$null

if ($container -eq "jobtracker-db") {
    Write-Host "✅ PostgreSQL läuft bereits!" -ForegroundColor Green
}
else {
    Write-Host "Starte neuen PostgreSQL Container..." -ForegroundColor Yellow
    docker run --name jobtracker-db `
        -e POSTGRES_USER=jobuser `
        -e POSTGRES_PASSWORD=pass123 `
        -e POSTGRES_DB=jobtracker `
        -p 5432:5432 `
        -d postgres:15

    Write-Host "⏳ Warte 5 Sekunden, bis PostgreSQL initialisiert ist..." -ForegroundColor Yellow
    Start-Sleep -Seconds 5
    Write-Host "✅ PostgreSQL gestartet!" -ForegroundColor Green
}

Write-Host ""

# ============================================================================
# STEP 2: Backend in separatem Terminal starten
# ============================================================================
Write-Host "🔧 Step 2: Starte Backend..." -ForegroundColor Cyan

$backendPath = "C:\Users\Tuncer\Desktop\job-tracker\job-tracker\backend"

Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$backendPath'; Write-Host '🚀 Starte Backend...' -ForegroundColor Cyan; .\mvnw.cmd spring-boot:run" -WindowStyle Normal

Write-Host "✅ Backend-Terminal öffnet sich..." -ForegroundColor Green
Write-Host "   Warte auf: 'Started BackendApplication'" -ForegroundColor Yellow

Start-Sleep -Seconds 3

Write-Host ""

# ============================================================================
# STEP 3: Frontend in separatem Terminal starten
# ============================================================================
Write-Host "⚛️  Step 3: Starte Frontend..." -ForegroundColor Cyan

$frontendPath = "C:\Users\Tuncer\Desktop\job-tracker\job-tracker\frontend"

if (!(Test-Path "$frontendPath\node_modules")) {
    Write-Host "📥 Installiere npm dependencies (erste Mal)..." -ForegroundColor Yellow
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$frontendPath'; npm install; npm start" -WindowStyle Normal
}
else {
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$frontendPath'; npm start" -WindowStyle Normal
}

Write-Host "✅ Frontend-Terminal öffnet sich..." -ForegroundColor Green
Write-Host "   Browser öffnet sich automatisch auf http://localhost:3000" -ForegroundColor Yellow

Write-Host ""
Write-Host "════════════════════════════════════════════════════════════════════" -ForegroundColor Green
Write-Host "✅ ALLES LÄUFT!" -ForegroundColor Green
Write-Host "════════════════════════════════════════════════════════════════════" -ForegroundColor Green
Write-Host ""
Write-Host "🌐 Frontend: http://localhost:3000" -ForegroundColor Cyan
Write-Host "🔌 Backend:  http://localhost:8080" -ForegroundColor Cyan
Write-Host "🗄️  Database: PostgreSQL (Docker)" -ForegroundColor Cyan
Write-Host ""
Write-Host "Warte auf weitere Anweisungen... (Fenster nicht schließen!)" -ForegroundColor Yellow
Read-Host "Drücke Enter zum Beenden"

Write-Host ""
Write-Host "🛑 Fahre alles herunter..." -ForegroundColor Yellow
docker stop jobtracker-db
Write-Host "✅ PostgreSQL gestoppt" -ForegroundColor Green
