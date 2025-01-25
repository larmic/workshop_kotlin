package de.larmic.workshop.kotlin.script.part2

class PersonService(private val personRepository: PersonRepository = PersonRepository()) {

    fun save(person: Person) {
        personRepository.save(person)
    }
}