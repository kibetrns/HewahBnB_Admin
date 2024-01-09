package data.model

import kotlinx.datetime.Instant

data class HouseReview(
    val reviewId: String,
    val reviewDescription: String,
    val reviewer: Customer,
    val ratingScore: RatingScore,
    val dateTimeReviewed: Instant,
)