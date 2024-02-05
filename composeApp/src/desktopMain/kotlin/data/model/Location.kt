package data.model

data class Location(
    val latitude: Double,
    val longitude: Double,
    val type: String = "GeoPoint"
)
