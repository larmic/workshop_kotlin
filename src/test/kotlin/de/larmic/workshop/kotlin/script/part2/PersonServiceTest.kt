package de.larmic.workshop.kotlin.script.part2

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class PersonServiceTest {

    private val personRepositoryMock = mockk<PersonRepository>()
    private val service = PersonService(personRepositoryMock)

    @Test
    fun `save a person`() {
        val person = (Person(name = "John", age = 37) as Created).person

        every { personRepositoryMock.save(person) } returns person

        service.save(person = person)

        verify(atLeast = 1, atMost = 1) { personRepositoryMock.save(person) }
    }
}