package de.larmic.workshop.kotlin.muster.part2

class Address private constructor(
    val street: String,
    val houseNumber: Int,
    val houseNumberAffix: String? = null,
    val zip: Int,
    val city: String,
) {

    companion object {
        operator fun invoke(
            street: String,
            houseNumber: Int,
            houseNumberAffix: String? = null,
            zip: Int,
            city: String,
        ): Result {
            val errors = mutableListOf<String>().apply {
                if (street.isBlank()) this.add("street must not be blank")
                if (city.isBlank()) this.add("city must not be blank")
                if (zip !in 10000..99999) this.add("zip must be between 10000 and 99999")
                if (houseNumber <= 0) this.add("houseNumber must be greater than 0")
            }

            return if (errors.isNotEmpty()) NotCreated(errors)
            else Created(Address(
                street = street.trim(),
                houseNumber = houseNumber,
                houseNumberAffix = houseNumberAffix?.trim(),
                zip = zip,
                city = city.trim(),
            ))
        }
    }

    sealed class Result
    data class Created(val address: Address) : Result()
    data class NotCreated(val errors: List<String>) : Result()

    fun getReadableAddress() = """
        $street $houseNumber $houseNumberAffix
        $zip $city
    """.trimIndent()
}