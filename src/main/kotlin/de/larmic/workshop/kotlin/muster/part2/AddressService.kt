package de.larmic.workshop.kotlin.muster.part2

class AddressService(private val repository: AddressEntityRepository = AddressEntityRepository()) {

    fun create(
        street: String,
        houseNumber: Int,
        houseNumberAffix: String? = null,
        zip: Int,
        city: String,
    ) = when (val result = Address(street = street, houseNumber = houseNumber, houseNumberAffix = houseNumberAffix, zip = zip, city = city)) {
        is Address.Created -> result.address.toEntity().let {
            if (it.existsInDb()) Failure(listOf("address already exists"))
            else Success(address = repository.save(it).toDomain())
        }

        is Address.NotCreated -> Failure(result.errors)
    }

    private fun Address.toEntity() = AddressEntity(street = street, houseNumber = houseNumber, houseNumberAffix = houseNumberAffix, zip = zip, city = city)
    private fun AddressEntity.toDomain() = Address(street = street, houseNumber = houseNumber, houseNumberAffix = houseNumberAffix, zip = zip, city = city).asAddress()
    private fun Address.Result.asAddress() = (this as Address.Created).address
    private fun AddressEntity.existsInDb() = repository.exists(this)

    sealed class Result
    data class Success(val address: Address) : Result()
    data class Failure(val errors: List<String>) : Result()
}