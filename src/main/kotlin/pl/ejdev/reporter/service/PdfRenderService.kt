package pl.ejdev.reporter.service

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import pl.ejdev.reporter.repository.jsUuid
import pl.ejdev.reporter.templates.HtmlTemplate
import pl.ejdev.reporter.templates.LessonsTemplateData
import pl.ejdev.reporter.templates.TemplateData
import pl.ejdev.reporter.templates.UsersTemplateData
import java.io.ByteArrayOutputStream

interface PdfRenderService {
    fun render(code: String): ByteArray
}

class PdfRenderServiceImpl(
    private val templates: Map<HtmlTemplate.Type, HtmlTemplate<TemplateData<*>, *>>,
    private val userService: UserService,
    private val lessonService: LessonService
) : PdfRenderService {

    override fun render(code: String): ByteArray = findTypeOrThrow(code)
        .let { type ->
            val data = resolveData(type)
            templates.getValue(type)
                .template(data)
                .let(::renderToBytes)
        }

    private fun resolveData(type: HtmlTemplate.Type): TemplateData<*> = when (type) {
        HtmlTemplate.Type.User -> UsersTemplateData(userService.getAll())
        HtmlTemplate.Type.Lesson -> LessonsTemplateData(lessonService.getByUserId(jsUuid))
    }

    private fun findTypeOrThrow(code: String): HtmlTemplate.Type =
        HtmlTemplate.Type.entries.find { it.name == code } ?: error("Unknown template $code")

    private fun renderToBytes(html: String) = ByteArrayOutputStream().use { stream ->
        PdfRendererBuilder().run {
            useFastMode()
            withHtmlContent(html, null)
            toStream(stream)
            run()
        }
        stream
    }.toByteArray()
}