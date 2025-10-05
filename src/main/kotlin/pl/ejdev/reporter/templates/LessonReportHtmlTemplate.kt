package pl.ejdev.reporter.templates

import arrow.core.getOrElse
import kotlinx.html.*
import pl.ejdev.reporter.model.Lesson

private const val TITLE = "Student Report Card"
private const val COURSE = "Course"
private const val SEMESTER = "Semester"
private const val CREDITS = "Credits"
private const val GRADE = "Grade"
private const val CODE = "Code"
private const val NAME = "Name"
private const val LETTER = "Letter"
private const val POINTS = "Points"
private const val TOTAL = "Total"
private const val GPA = "GPA"

class LessonsTemplateData(
    val lessons: List<Lesson>
) : TemplateData<List<Lesson>>(lessons)

object LessonReportHtmlTemplate : HtmlTemplate<LessonsTemplateData, List<Lesson>>() {

    override val type: Type = Type.Lesson

    override fun template(data: LessonsTemplateData): String = html(TITLE) {
        val lessons = data.lessons
        body {
            h2 { +TITLE }
            table {
                tableHeader()
                tableRows(lessons)
                tableFooter(lessons)
            }
        }
    }

    private fun TABLE.tableHeader() {
        thead {
            tr {
                td { colSpan = "3"; +COURSE }
                td { rowSpan = "2"; +SEMESTER }
                td { rowSpan = "2"; +CREDITS }
                td { colSpan = "2"; +GRADE }
            }
            tr {
                td { +CODE }
                td { colSpan = "2"; +NAME }
                td { +LETTER }
                td { +POINTS }
            }
        }
    }

    private fun TABLE.tableRows(lessons: List<Lesson>) {
        tbody {
            lessons.forEach { lesson ->
                tr {
                    td { +lesson.code }
                    td { colSpan = "2"; +lesson.name }
                    td { +lesson.semester }
                    td { +lesson.credits.toString() }
                    td { +lesson.grade() }
                    td { +lesson.points() }
                }
            }
        }
    }

    private fun TABLE.tableFooter(lessons: List<Lesson>) {
        tfoot {
            tr {
                td { colSpan = "4"; classes += "footer"; +TOTAL }
                td { +lessons.creditsSum() }
                td { colSpan = "2"; +lessons.sumPoints() }
            }
            tr {
                td { colSpan = "4"; classes += "footer"; +GPA }
                td { colSpan = "3"; +"3.73 / 4.0" }
            }
        }
    }

    private fun List<Lesson>.creditsSum(): String = this.sumOf { lesson -> lesson.credits }.toString()

    private fun List<Lesson>.sumPoints(): String = this.sumOf { lesson -> lesson.points.getOrElse { 0.0 } }.toString()

    private fun Lesson.points(): String = this.points.map { it.toString() }.getOrElse { "" }

    private fun Lesson.grade(): String = this.grade
        .map { grade -> grade.letter.name + grade.sign.map { it.name }.getOrElse { "" } }
        .getOrElse { "" }
}