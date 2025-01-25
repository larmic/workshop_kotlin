package de.larmic.workshop.kotlin.muster.part2

import io.kotest.matchers.collections.shouldContainOnly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AddressServiceTest {

    private val repositoryMock = mockk<AddressEntityRepository>()
    private val addressService = AddressService(repositoryMock)

    init {
        // default mocking behavior - can be overridden in test method
        every { repositoryMock.save(any()) } answers { firstArg() }
        every { repositoryMock.exists(createAddressEntity()) } returns false
    }

    @Test
    fun `add not existing address`() {
        addressService.createWithTestFixtures().let {
            it.shouldBeInstanceOf<AddressService.Success>()
            it.address.street shouldBe TEST_FIXTURE_STREET
            it.address.houseNumber shouldBe TEST_FIXTURE_HOUSE_NUMER
            it.address.houseNumberAffix shouldBe TEST_FIXTURE_HOUSE_NUMER_AFFIX
            it.address.zip shouldBe TEST_FIXTURE_ZIP
            it.address.city shouldBe TEST_FIXTURE_CITY
        }
    }

    @Test
    fun `add existing address`() {
        // override default mocking behavior
        every { repositoryMock.exists(createAddressEntity()) } returns true

        addressService.createWithTestFixtures().let {
            it.shouldBeInstanceOf<AddressService.Failure>()
            it.errors.shouldContainOnly("address already exists")
        }
    }

    @Nested
    inner class StreetTests {

        @ParameterizedTest
        @ValueSource(strings = ["", " ", "  ", "     "])
        fun `street is not valid`(value: String) {
            addressService.createWithTestFixtures(street = value).let {
                it.shouldBeInstanceOf<AddressService.Failure>()
                it.errors.shouldContainOnly("street must not be blank")
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["", " ", "  ", "     "])
        fun `street is not trimmed`(whitespace: String) {
            addressService.createWithTestFixtures(street = "$whitespace$TEST_FIXTURE_STREET$whitespace").let {
                it.shouldBeInstanceOf<AddressService.Success>()
                it.address.street shouldBe TEST_FIXTURE_STREET
                it.address.houseNumber shouldBe TEST_FIXTURE_HOUSE_NUMER
                it.address.houseNumberAffix shouldBe TEST_FIXTURE_HOUSE_NUMER_AFFIX
                it.address.zip shouldBe TEST_FIXTURE_ZIP
                it.address.city shouldBe TEST_FIXTURE_CITY
            }
        }
    }

    @Nested
    inner class CityTests {
        @ParameterizedTest
        @ValueSource(strings = ["", " ", "  ", "     "])
        fun `city is not valid`(value: String) {
            addressService.createWithTestFixtures(city = value).let {
                it.shouldBeInstanceOf<AddressService.Failure>()
                it.errors.shouldContainOnly("city must not be blank")
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["", " ", "  ", "     "])
        fun `city is not trimmed`(whitespace: String) {
            addressService.createWithTestFixtures(city = "$whitespace$TEST_FIXTURE_CITY$whitespace").let {
                it.shouldBeInstanceOf<AddressService.Success>()
                it.address.street shouldBe TEST_FIXTURE_STREET
                it.address.houseNumber shouldBe TEST_FIXTURE_HOUSE_NUMER
                it.address.houseNumberAffix shouldBe TEST_FIXTURE_HOUSE_NUMER_AFFIX
                it.address.zip shouldBe TEST_FIXTURE_ZIP
                it.address.city shouldBe TEST_FIXTURE_CITY
            }
        }
    }

    @Nested
    inner class HouseNumberTests {
        @ParameterizedTest
        @ValueSource(ints = [-10, -2, -1])
        fun `house number is not valid`(value: Int) {
            addressService.createWithTestFixtures(houseNumber = value).let {
                it.shouldBeInstanceOf<AddressService.Failure>()
                it.errors.shouldContainOnly("houseNumber must be greater than 0")
            }
        }

        @ParameterizedTest
        @ValueSource(strings = ["", " ", "  ", "     "])
        fun `house number affix is not trimmed`(whitespace: String) {
            addressService.createWithTestFixtures(houseNumberAffix = "$whitespace$TEST_FIXTURE_HOUSE_NUMER_AFFIX$whitespace").let {
                it.shouldBeInstanceOf<AddressService.Success>()
                it.address.street shouldBe TEST_FIXTURE_STREET
                it.address.houseNumber shouldBe TEST_FIXTURE_HOUSE_NUMER
                it.address.houseNumberAffix shouldBe TEST_FIXTURE_HOUSE_NUMER_AFFIX
                it.address.zip shouldBe TEST_FIXTURE_ZIP
                it.address.city shouldBe TEST_FIXTURE_CITY
            }
        }
    }

    @Nested
    inner class ZipTests {
        @ParameterizedTest
        @ValueSource(ints = [9999, 100000])
        fun `zip is not valid`(value: Int) {
            addressService.createWithTestFixtures(zip = value).let {
                it.shouldBeInstanceOf<AddressService.Failure>()
                it.errors.shouldContainOnly("zip must be between 10000 and 99999")
            }
        }
    }

    private fun AddressService.createWithTestFixtures(
        street: String = TEST_FIXTURE_STREET,
        houseNumber: Int = TEST_FIXTURE_HOUSE_NUMER,
        houseNumberAffix: String? = TEST_FIXTURE_HOUSE_NUMER_AFFIX,
        zip: Int = TEST_FIXTURE_ZIP,
        city: String = TEST_FIXTURE_CITY,
    ) = this.create(street = street, houseNumber = houseNumber, houseNumberAffix = houseNumberAffix, zip = zip, city = city)
}