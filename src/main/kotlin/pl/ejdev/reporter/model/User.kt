package pl.ejdev.reporter.model

import java.util.UUID

data class User(
    val id: UUID,
    val firstName: String,
    val lastName: String,
)
