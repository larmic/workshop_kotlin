# workshop_kotlin

Hilfe zu Maven: https://kotlinlang.org/docs/maven.html
Spring initializr bringt alles mit: https://start.spring.io/

## Agenda

Die eigentliche Agenda geht aus den Folien hervor. Hier befinden sich
zusätzliche Notizen.

### Part 1 - Einfach nur zuhören

Vortrag _Storytime - von Java zu Kotlin_

### Part 2 - Einführung in die Basics

* var / val
* classes
* Null-Safety
* data-classes
* equality
* init {}
* default values
* multiline-strings

[Hier](misc/script_part1_einführung.md) befinden sich die Notizen für den Vortragenden.

### Part 3 - Selbst arbeiten

Die Story wird vorgestellt und dann in kleinen Gruppen umgesetzt.
Einzelne Gruppen stellen dann ihre Lösung vor und wir disktutieren gemeinsam.

*Frage*: Auf wie viele Zeilen Code kommen wir jetzt?

### Part 4 - tiefere Kotlin-Features

* Extension functions
  * Sichtbarkeiten
  * gerne für mappings .mapToDomain(), .mapToDto(), .mapToEntity()
* invoke-functions
* Listen
* switch
* ?: elvis operator und guard pattern
* test
  * mockk
  * kotest
  * Best-Practise: test factories

[Hier](misc/script_part4_deeper.md) befinden sich die Notizen für den Vortragenden.

### Part 5

Neue Story wird vorgestellt und in kleinen Gruppen umgesetzt.
Einzelne Gruppen stellen dann ihre Lösung vor und wir disktutieren gemeinsam.

### Part 6

Im Ausblick für einen weiteren Schulungsslot
* let, apply
* lateinit
* dsl
* nachteile
  * keine package-sichtbarkeit