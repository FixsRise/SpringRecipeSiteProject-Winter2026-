
Get-Content .env | ForEach-Object {
    if ($_ -match "=") {
        $parts = $_ -split "="
        [System.Environment]::SetEnvironmentVariable($parts[0], $parts[1], "Process")
    }
}

./mvnw spring-boot:run
