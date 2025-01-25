package de.larmic.workshop.kotlin.muster.part1

class Address(
    val street: String,
    val houseNumber: Int,
    val houseNumberAffix: String? = null,
    val zip: Int,
    val city: String,
) {
    init {
        require(street.isNotBlank()) { "street must not be blank" }
        require(city.isNotBlank()) { "city must not be blank" }
        require(zip in 10000..99999) { "zip must be between 10000 and 99999" }
        require(houseNumber > 0) { "houseNumber must be greater than 0" }
    }

    fun getReadableAddress() = """
        $street $houseNumber $houseNumberAffix
        $zip $city
    """.trimIndent()
}

fun usage() {
    Address(
        street = "Musterstraße",
        houseNumber = 13,
        houseNumberAffix = "A",
        zip = 55555,
        city = "Entenhausen",
    ).let { println(it.getReadableAddress()) }
}