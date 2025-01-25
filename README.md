# workshop_kotlin

Hilfe zu Maven: https://kotlinlang.org/docs/maven.html  
Spring initializr bringt alles mit: https://start.spring.io/

## Agenda

Die eigentliche Agenda geht aus den Folien hervor. Hier befinden sich
zusätzliche Notizen.

### Part 1 - Einfach nur zuhören

Teaser _Storytime_

### Part 2 - Einführung in die Basics

* var / val
* classes
* Null-Safety
* data-classes
* equality
* init {}
* default values
* multiline-strings
* named parameters
* direct return

[Hier](misc/script_part1_einführung.md) befinden sich die Notizen für den Vortragenden.  
[Und hier](src/main/kotlin/de/larmic/workshop/kotlin/script/part1/Person.kt) findest
Du die Umsetzung der Einführung.

### Part 3 - Selbst arbeiten

Die Story wird vorgestellt und dann in kleinen Gruppen umgesetzt.
Einzelne Gruppen stellen dann ihre Lösung vor und wir disktutieren gemeinsam.

*Frage*: Auf wie viele Zeilen Code kommen wir jetzt?

[Eine Musterlösung](src/main/kotlin/de/larmic/workshop/kotlin/muster/part1/Address.kt).  

### Part 4 - tiefere Kotlin-Features

* Extension functions
  * Sichtbarkeiten
  * gerne für mappings .mapToDomain(), .mapToDto(), .mapToEntity()
* invoke-functions
* sealed classes
* switch / when
* Listen
* test
  * mockk (spring)
  * kotest
  * Best-Practise: test factories

[Hier](misc/script_part4_deeper.md) befinden sich die Notizen für den Vortragenden.

### Part 5

Neue Story wird vorgestellt und in kleinen Gruppen umgesetzt.
Einzelne Gruppen stellen dann ihre Lösung vor und wir disktutieren gemeinsam.

Idee: Service zum Speichern von Adressen mit Prüfung auf Duplikate?

### Part 6

Im Ausblick für einen weiteren Schulungsslot
* lateinit
* dsl
* nachteile
  * keine package-sichtbarkeit