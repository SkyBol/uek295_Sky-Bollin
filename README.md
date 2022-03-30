# uek295_Sky-Bollin

## Setup

### Anforderungen

- Docker Desktop
- IntelliJ
- Postman

### Donwload

Als erster gilt es dieses Repository herunterzuladen. Dies kann nach vorliebe über einen clone oder den [.zip Download](https://github.com/SkyBol/uek295_Sky-Bollin/archive/refs/heads/main.zip) geschehen. In den Ordnern befindet sich das Aktuellste im Ordner "final". Der Rest kann entsorgt werden, je nach wünschen.
Der Ordner sbdemo01 soll in IntelliJ geöffnet werden, das Programm kann aber im  aktuellen Zustannd noch nicht ausgeführt werden.

Die Datei mit dem Namen "üK295-Tests.postman_collection.json" kann in Postman importiert werden.

### Docker Desktop

Es wird eine funktionierende Postgres Datenbank im Localhost erwartet. Diese besitzt folgende Eingenschaften:
- Name: uek295db
- Passwort: 30DBrootPW48
- Port: 5432

Sie kann mithilfe von Docker Desktop aufgesetzt werden. Dieser Befehl erschafft eine: (In CMD ausführen:)

docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=30DBrootPW48 --name uek295db postgres

## Programm

Nach dem Starten braucht man ein Logging um weiter mit Spring Boot zu interagieren. Dazu gibt es das Standartlogging:

- Name: Admin
- Passwort: 132

Diese Daten sind auch in Postman zu finden, sowie in der Starterklasse des Projektes. Es besitzt die Rolle ADMIN, welche alle Rechte besitzt. Die Rollen und dazugehörigen Rechte wären:

- ADMIN
    - POST
    - UPDATE
    - DELETE
    - ACCESS
- STAFF
    - UPDATE
    - ACCESS
- VIEWER
    - ACCESS

Die Rechte beziehen sich nur auf Bestellungen, bis auf ACCESS, welches überall giltet.

