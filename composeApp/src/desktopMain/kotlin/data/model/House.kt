package data.model

import androidx.compose.ui.graphics.painter.Painter

data class House(
    val houseId: String,
    val images: List<Painter>, //TODO("Change this to a list of String")
    val name: String,
    val locationName: String,
    val location: Location,
    val distFrmMe: Double,
    val rating: Int,
    val amount: Double,
    val payFrequency: PayFrequency,
    val availability: Availability,
    val description: String,
    val owner: HouseOwner,
    val reviews: List<HouseReview>,
    val amenities: List<Amenity>,
    val houseType: HouseType,
    val roomCount: Int,
    val bathroomsCount: Int,
    val size: Double,
    val furnishType:FurnishType,
    val houseBooking: HouseBooking
    )