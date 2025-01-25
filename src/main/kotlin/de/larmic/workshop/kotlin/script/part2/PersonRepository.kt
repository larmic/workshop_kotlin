package de.larmic.workshop.kotlin.script.part2

class PersonRepository(private val persons: MutableList<Person> = mutableListOf()) {

    fun save(person: Person) : Person {
        persons.add(person)
        return person
    }

}