package pl.ejdev.reporter.model

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import java.util.*

data class Lesson(
    val userId: UUID,
    val code: String,
    val name: String,
    val semester: String,
    val credits: Double,
    val grade: Option<Grade> = None,
    val points: Option<Double> = None
)

data class Grade(
    val letter: Letter,
    val sign: Option<Sign> = None
) {
    companion object {
        fun some(letter: Letter): Option<Grade> = Some(Grade(letter))
        fun some(letter: Letter, sign: Sign): Option<Grade> = Some(Grade(letter, Some(sign)))
    }
}

enum class Letter {
    A, B, C, D, E
}

enum class Sign {
    `+`, `-`
}