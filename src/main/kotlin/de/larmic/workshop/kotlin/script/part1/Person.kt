package de.larmic.workshop.kotlin.script.part1

enum class Geschlecht {
    M, W, D
}

data class Person(val name: String,
                  val age: Int,
                  val geschlecht: Geschlecht = Geschlecht.M
) {
    init {
        require(age in 0..100) {
            "Age must be between 0 and 100"
        }
        require(name.isNotBlank()) {
            "Name must not be blank"
        }
    }

    fun readablePerson() = """
        Name: $name
        Age: $age
        Geschlecht: $geschlecht
    """.trimIndent()
}

fun usage() {
    val john = Person(name = "John", age = 49)
    val maria = Person(name = "Maria", age = 29, geschlecht = Geschlecht.W)

    println("Hi \n${john.readablePerson()}\n${maria.readablePerson()}")
}