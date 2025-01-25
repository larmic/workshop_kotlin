package de.larmic.workshop.kotlin.muster.part2

class AddressEntityRepository(private var addresses: MutableList<AddressEntity> = mutableListOf()) {

    fun save(address: AddressEntity): AddressEntity {
        addresses.add(address)
        return address
    }

    fun exists(address: AddressEntity) = addresses.contains(address)

}