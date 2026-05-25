# Start Job Tracker - Frontend + Backend + PostgreSQL

Write-Host "Starting Job Tracker..." -ForegroundColor Yellow

# Step 1: PostgreSQL
Write-Host "Step 1: Starting PostgreSQL with Docker..." -ForegroundColor Cyan
$container = docker ps --filter "name=jobtracker-db" --format "{{.Names}}" 2>$null

if ($container -eq "jobtracker-db") {
    Write-Host "PostgreSQL already running!" -ForegroundColor Green
} else {
    Write-Host "Starting PostgreSQL container..." -ForegroundColor Yellow
    docker run --name jobtracker-db `
        -e POSTGRES_USER=jobuser `
        -e POSTGRES_PASSWORD=pass123 `
        -e POSTGRES_DB=jobtracker `
        -p 5432:5432 `
        -d postgres:15
    Start-Sleep -Seconds 5
    Write-Host "PostgreSQL started!" -ForegroundColor Green
}

# Step 2: Backend
Write-Host "Step 2: Starting Backend..." -ForegroundColor Cyan
$backendPath = "C:\Users\Tuncer\Desktop\job-tracker\job-tracker\backend"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$backendPath'; .\mvnw.cmd spring-boot:run"
Write-Host "Backend terminal opened..." -ForegroundColor Green
Start-Sleep -Seconds 3

# Step 3: Frontend
Write-Host "Step 3: Starting Frontend..." -ForegroundColor Cyan
$frontendPath = "C:\Users\Tuncer\Desktop\job-tracker\job-tracker\frontend"

$nodeModulesPath = Join-Path -Path $frontendPath -ChildPath "node_modules"
if (!(Test-Path $nodeModulesPath)) {
    Write-Host "Installing npm dependencies..." -ForegroundColor Yellow
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$frontendPath'; npm install; npm start"
} else {
    Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd '$frontendPath'; npm start"
}

Write-Host "Frontend terminal opened..." -ForegroundColor Green
Write-Host ""
Write-Host "All running!" -ForegroundColor Green
Write-Host "Frontend: http://localhost:3000" -ForegroundColor Cyan
Write-Host "Backend:  http://localhost:8080" -ForegroundColor Cyan
Write-Host ""
Read-Host "Press Enter to stop everything"

docker stop jobtracker-db
Write-Host "PostgreSQL stopped!" -ForegroundColor Green
