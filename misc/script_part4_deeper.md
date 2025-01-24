# Extension functions

Weiter mit der [Person](src/main/kotlin/de/larmic/workshop/kotlin/script/part1/Person.kt)

```kotlin
private fun Int.isAge() = this in 0..100
```

# invoke-functions + sealed classes

-> Setze leeren Namen ein und erhalte eine Exception
-> Setze Namen mit trailing spaces ein

```kotlin
val person = Person(name = "", age = 27, geschlecht = Geschlecht.M)
val person = Person(name = " Hans ", age = 27, geschlecht = Geschlecht.M)
```