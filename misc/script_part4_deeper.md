# Extension functions

Weiter mit der [Person](../src/main/kotlin/de/larmic/workshop/kotlin/script/part1/Person.kt)

```kotlin
private fun Int.isAge() = this in 0..100
```

# invoke-functions + sealed classes

-> Setze leeren Namen ein und erhalte eine Exception
-> Setze Namen mit trailing spaces ein
-> Exception Driven

```kotlin
val person = Person(name = "", age = 27, geschlecht = Geschlecht.M)
val person = Person(name = " Hans ", age = 27, geschlecht = Geschlecht.M)
```

# Sealed Classes und switch (when)

Eigentlich wie Java nur besser

```kotlin
sealed class Result
class Created(val person: Person) : Result()
class NotCreated(val errors: List<String>) : Result()

operator fun invoke(
    name: String,
    age: Int,
    geschlecht: Geschlecht = Geschlecht.M,
): Result {
    val errors = mutableListOf<String>().apply {
        if (name.isBlank()) this.add("Name must not be blank")
        if (!age.isAge()) this.add("Age must be between 0 and 100")
    }

    return if (errors.isNotEmpty()) NotCreated(errors)
    else Created(Person(name = name.trimIndent(), age = age, geschlecht = geschlecht))
}

// main
when (val result = Person(name = "  ", age = -1, geschlecht = Geschlecht.M)) {
    is Created -> println("Created ${result.person.readablePerson()}")
    is NotCreated -> println("Person not created ${result.errors.joinToString(separator = ";")}")
}
```

# Testfactories

```kotlin
fun createPerson(name: String = "Sarah",
                 age: Int = 98,
                 geschlecht: Geschlecht = Geschlecht.W)
= Person(name = name, age = age, geschlecht = geschlecht)

// main
listOf(
    createPerson(),
    createPerson(name = "Thomas", geschlecht = Geschlecht.M),
    createPerson(name = "Hans", age = 19, geschlecht = Geschlecht.M),
    createPerson(name = "Noah", age = 10, geschlecht = Geschlecht.M),
    createPerson(name = "invalid", age = -10, geschlecht = Geschlecht.D)
)
```

# Listen

Eigentlich wie Java nur kürzer.

```kotlin
listOf(
        createPerson(),
        createPerson(name = "Thomas", geschlecht = Geschlecht.M),
        createPerson(name = "Hans", age = 19, geschlecht = Geschlecht.M),
        createPerson(name = "Noah", age = 10, geschlecht = Geschlecht.M),
        createPerson(name = "invalid", age = -10, geschlecht = Geschlecht.D)
    ).filterIsInstance<Created>() // -> hier dann map {it.person} einfügen
        .filter { it.person.age > 18 }
        .filter { it.person.geschlecht == Geschlecht.M }
        .forEach { println(it.person.readablePerson()) }
```

# Tests (kotest, mockk)