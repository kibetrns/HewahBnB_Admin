package data.model

import kotlinx.datetime.Instant

data class HouseBooking(
    val bookingId: String,
    val checkIn: Instant,
    val checkOut: Instant,
    val totalAmount: Int,
    val customerId: String,
    val houseOwnerId: String
    )
