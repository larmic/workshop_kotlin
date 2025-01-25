package de.larmic.workshop.kotlin.muster.part2

const val TEST_FIXTURE_STREET = "Geldspeicherstraße"
const val TEST_FIXTURE_HOUSE_NUMER = 1
const val TEST_FIXTURE_HOUSE_NUMER_AFFIX = "a"
const val TEST_FIXTURE_ZIP = 12345
const val TEST_FIXTURE_CITY = "Entenhausen"

fun createAddressEntity(
    street: String = TEST_FIXTURE_STREET,
    houseNumber: Int = TEST_FIXTURE_HOUSE_NUMER,
    houseNumberAffix: String? = TEST_FIXTURE_HOUSE_NUMER_AFFIX,
    zip: Int = TEST_FIXTURE_ZIP,
    city: String = TEST_FIXTURE_CITY,
) = AddressEntity(street = street, houseNumber = houseNumber, houseNumberAffix = houseNumberAffix, zip = zip, city = city)