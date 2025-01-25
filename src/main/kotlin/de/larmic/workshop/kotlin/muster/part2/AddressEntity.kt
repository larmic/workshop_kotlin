package de.larmic.workshop.kotlin.muster.part2

data class AddressEntity(
    val street: String,
    val houseNumber: Int,
    val houseNumberAffix: String? = null,
    val zip: Int,
    val city: String,
)
