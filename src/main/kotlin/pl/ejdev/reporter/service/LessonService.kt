package pl.ejdev.reporter.service

import pl.ejdev.reporter.model.Lesson
import pl.ejdev.reporter.repository.LessonDao
import java.util.UUID

interface LessonService {
    fun getByUserId(id: UUID): List<Lesson>
}

class LessonServiceImpl(
    private val lessonDao: LessonDao
): LessonService {
    override fun getByUserId(id: UUID): List<Lesson> = lessonDao.getAllBy(id)
}