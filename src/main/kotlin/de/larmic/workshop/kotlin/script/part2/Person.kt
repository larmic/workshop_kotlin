package de.larmic.workshop.kotlin.script.part2

enum class Geschlecht { M, W, D }

data class Person private constructor(
    val name: String,
    val age: Int,
    val geschlecht: Geschlecht = Geschlecht.M,
) {
    companion object {
        operator fun invoke(
            name: String,
            age: Int,
            geschlecht: Geschlecht = Geschlecht.M,
        ): Result {
            val errors = mutableListOf<String>().apply {
                if (name.isBlank()) this.add("Name must not be blank")
                if (age.isNoValidAge()) this.add("Age must be between 0 and 100")
            }

            return if (errors.isNotEmpty()) NotCreated(errors)
            else Created(Person(name = name.trim(), age = age, geschlecht = geschlecht))
        }
    }

    fun readablePerson() = """
        Name: $name
        Age: $age
        Geschlecht: $geschlecht
    """.trimIndent()
}

private fun Int.isNoValidAge() = this !in 0..100

sealed class Result
class Created(val person: Person) : Result()
class NotCreated(val errors: List<String>) : Result()

fun createPerson(name: String = "Sarah",
                 age: Int = 98,
                 geschlecht: Geschlecht = Geschlecht.W)
    = Person(name = name, age = age, geschlecht = geschlecht)

fun usage() {
    listOf(
        createPerson(),
        createPerson(name = "Thomas", geschlecht = Geschlecht.M),
        createPerson(name = "Hans", age = 19, geschlecht = Geschlecht.M),
        createPerson(name = "Noah", age = 10, geschlecht = Geschlecht.M),
        createPerson(name = "invalid", age = -10, geschlecht = Geschlecht.D)
    ).filterIsInstance<Created>()
        .map { it.person }
        .filter { it.age > 18 }
        .filter { it.geschlecht == Geschlecht.M }
        .forEach { println(it.readablePerson()) }
}