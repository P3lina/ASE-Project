## This contains some requirements with explanation for the ASE project

<br/>

# Clean Architecture

Die eigentlich Anwendung, technologie-unabhängig im Kern
Außenrum sind Sachen wie Datenbanken GUI usw
Abhängigkeiten von außen nach Innen, Aufrufe zur Laufzeit können beidseitig sein
-> Innere Schicht weiß nichts von der äußeren

Je konkreter der Code ist, desto weiter außen liegt er
![Clean Architecture layers](images/Clean%20Architecture%20layers.jpg)

**Schicht 4**: mathematische Konzepte, Schicht häufig nicht notwendig
<br>

**Schicht 3**: v.a. Entities, Geschäftslogik
<br>

**Schicht 2**: anwendungsspezifische Geschäftslogik, steuert den Fluss der Daten und Aktionen von und zu den Entities, spezifisch für die Anwendung, Änderungen an dieser Schicht beeinflussen Schicht 3 NICHT
<br>

**Schicht 1**: Diese Schicht vermittelt Aufrufe und Daten an die inneren Schichten, bereitet Daten für Benutzeroberfläche vor
<br>

**Schicht 0**: greift nur Auf die Adapter zu, Benutzeroberfläche, wenig Code (Delegationscode an Adapter)

Konkrete Implementierung: entweder pro Schicht ein package oder ein eigenes Projekt

# DDD (Domain Driven Design)

Hauptbestandteile: UL, VO, Entities
<br>
<br>

### UL (Ubiquitous Language, wichtigstes Konzept des DDD)

Erstellen von Glossar für UL

Artefakte mit Projektsprache:

- Anforderungsdokument bzw. Anforderungen
- Benutzerhandbuch
- Fehlerberichte und Change Requests
- Benutzeroberfläche
- Fachmodell (Domänenmodell)
- Softwaredesign (Klassen- und Methoden)
- Sourcecode (Implementierung)
- Entwicklungsinterne Kommunikation (Issues)
  <br>

### Value Objects

- Kapselt ein Wertekonzep, wie Klasse Planet: Masse, Radius,…
- Sind unveränderlich
- Klasse als final deklarieren, alle Attribute sind automatisch final
  <br>

### Entities

- Veränderlich
- gleiche Daten != gleiches Entity
- eindeutig identifizierbar durch natürliche oder Surrogatschlüssel
- Entities sollten so viel wie möglich in VO auslagern
  <br>

### Aggregate

- beinhalten ein oder mehrere Entities bzw. Value Objects
- ein Entity ist Aggregat Root (AR) und verwaltet alle Zugriffe auf das Aggregat und stellt dabei sicher, dass Domänenregeln eingehalten werden
  <br>

### Repository

- Zugriff auf Speicher wird von Repository verborgen
- normalerweise pro Aggregat ein Repository
- Repositories geben immer AR des Aggregats zurück
- Definition (techn. Interface) ist Teil des Domain Codes, implementiert wird es außerhalb
  <br>

### Factories

- einziger Zweck: Erzeugen von Objekten

# Programmierprinzipien

### SOLID

![SOLID principle](images/SOLID.jpg)
<br>

### GRASP (General Responsibility Assignment Software Patterns)

**Low Coupling**: geringe Abhängigkeit zwischen Objekten
<br>

**High Cohesion**: Hohe semantische Nähe der Elemente einer Klasse
<br>

### DRY

Don’t repeat yourself
