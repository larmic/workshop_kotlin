package de.larmic.workshop.kotlin.muster.part1

import java.util.UUID

class Address(
    val identifier: UUID = UUID.randomUUID(),
    val street: String,
    val houseNumber: Int,
    val houseNumberAffix: String? = null,
    val zip: Int,
    val city: String,
    val district: String? = null,
) {
    init {
        require(street.isNotBlank()) { "street must not be blank" }
        require(city.isNotBlank()) { "city must not be blank" }
        require(zip in 10000..99999) { "houseNumber must be between 10000 and 99999" }
        require(houseNumber > 0) { "houseNumber must be greater than 0" }
    }

    fun getReadableAddress() = """
        $street $houseNumber $houseNumberAffix
        $zip $city ($district)
    """.trimIndent()
}

fun usage() {
    Address(
        street = "Musterstraße",
        houseNumber = 13,
        houseNumberAffix = "A",
        zip = 55555,
        city = "Entenhausen",
        district = "Schwimmenten",
    ).let { println(it.getReadableAddress()) }
}