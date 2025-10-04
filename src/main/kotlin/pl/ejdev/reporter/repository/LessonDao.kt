package pl.ejdev.reporter.repository

import arrow.core.Some
import pl.ejdev.reporter.model.Grade
import pl.ejdev.reporter.model.Lesson
import pl.ejdev.reporter.model.Letter.A
import pl.ejdev.reporter.model.Letter.B
import pl.ejdev.reporter.model.Sign.`+`
import pl.ejdev.reporter.model.Sign.`-`
import java.util.*

interface LessonDao {
    fun getAllBy(userId: UUID): List<Lesson>
}

class MockLessonDao : LessonDao {

    private val semester = "Fall 2015"

    private val data = listOf(
        Lesson(
            userId = jsUuid,
            code = "PHIL 105",
            name = "Ethics",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(A, `+`),
            points = Some(10.98)
        ),
        Lesson(
            userId = jsUuid,
            code = "ECE 310",
            name = "Digital Signal Processing",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(A),
            points = Some(12.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "CS 373",
            name = "Combinatorial Algorithms",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(B, `-`),
            points = Some(9.99)
        ),
        Lesson(
            userId = jsUuid,
            code = "MATH 225",
            name = "Multi-Variable Calculus",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(A, `-`),
            points = Some(10.98)
        ),
        Lesson(
            userId = jsUuid,
            code = "CS 101",
            name = "Introduction to Programming",
            semester = semester,
            credits = 4.0,
            grade = Grade.some(A),
            points = Some(16.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "HIST 210",
            name = "World History",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(B, `+`),
            points = Some(9.99)
        ),
        Lesson(
            userId = jsUuid,
            code = "PHYS 211",
            name = "Physics: Mechanics",
            semester = semester,
            credits = 4.0,
            grade = Grade.some(A, `-`),
            points = Some(15.32)
        ),
        Lesson(
            userId = jsUuid,
            code = "CHEM 101",
            name = "General Chemistry",
            semester = semester,
            credits = 4.0,
            grade = Grade.some(B),
            points = Some(12.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "CS 225",
            name = "Data Structures",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(A),
            points = Some(12.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "ENGL 201",
            name = "Literary Analysis",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(B, `+`),
            points = Some(9.99)
        ),
        Lesson(
            userId = jsUuid,
            code = "STAT 200",
            name = "Introductory Statistics",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(A),
            points = Some(12.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "BIO 150",
            name = "Molecular Biology",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(B, `-`),
            points = Some(8.99)
        ),
        Lesson(
            userId = jsUuid,
            code = "CS 374",
            name = "Algorithms & Models of Computation",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(A, `-`),
            points = Some(10.98)
        ),
        Lesson(
            userId = jsUuid,
            code = "ECON 102",
            name = "Macroeconomics",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(B),
            points = Some(9.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "ART 130",
            name = "Introduction to Visual Arts",
            semester = semester,
            credits = 2.0,
            grade = Grade.some(A),
            points = Some(8.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "MATH 241",
            name = "Linear Algebra",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(A),
            points = Some(12.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "PHIL 202",
            name = "Logic",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(B, `+`),
            points = Some(9.99)
        ),
        Lesson(
            userId = jsUuid,
            code = "CS 440",
            name = "Artificial Intelligence",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(A),
            points = Some(12.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "PSYC 100",
            name = "Introduction to Psychology",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(B),
            points = Some(9.0)
        ),
        Lesson(
            userId = jsUuid,
            code = "CS 450",
            name = "Operating Systems",
            semester = semester,
            credits = 3.0,
            grade = Grade.some(A, `-`),
            points = Some(10.98)

        )

    )

    override fun getAllBy(userId: UUID): List<Lesson> = data.filter { it.userId == userId }

}