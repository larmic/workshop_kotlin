package de.larmic.workshop.kotlin.script.part2

import io.kotest.matchers.collections.shouldContainOnly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PersonTest {

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
}