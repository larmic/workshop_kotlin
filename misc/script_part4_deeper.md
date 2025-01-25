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
    else Created(Person(name = name.trim(), age = age, geschlecht = geschlecht))
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

# Tests kotest

```kotlin
@ParameterizedTest
@ValueSource(strings = ["John", " John ", "    John    "])
fun `trim name`(name: String) {
    Person(name = name, age = 10).let {
        it.shouldBeInstanceOf<Created>() // auto cast
        it.person.name shouldBe "John"
        it.person.age shouldBe 10
    }
}

@ParameterizedTest
@ValueSource(ints = [-1, 101])
fun `invalid ages`(age: Int) {
    Person(name = "John", age = age).let {
        it.shouldBeInstanceOf<NotCreated>()
        it.errors.shouldContainOnly("Age must be between 0 and 100")
    }
}
```

# Test mockk

Vorbereitung

```kotlin
class PersonRepository(private val persons: MutableList<Person> = mutableListOf()) {

    fun save(person: Person) : Person {
        persons.add(person)
        return person
    }
}

class PersonService(private val personRepository: PersonRepository = PersonRepository()) {

    fun save(person: Person) {
        personRepository.save(person)
    }
}
```

Test ausführen und Fehler analysieren

```kotlin
class PersonServiceTest {

    private val personRepositoryMock = mockk<PersonRepository>()
    private val service = PersonService(personRepositoryMock)

    @Test
    fun `save a person`() {
        val person = (Person(name = "John", age = 37) as Created).person

        service.save(person = person)
    }
}
```

Fehler korrigieren
```kotlin
@Test
fun `save a person`() {
    val person = (Person(name = "John", age = 37) as Created).person

    every { personRepositoryMock.save(person) } returns person

    service.save(person = person)

    verify(atLeast = 1, atMost = 1) { personRepositoryMock.save(person) }
}
```