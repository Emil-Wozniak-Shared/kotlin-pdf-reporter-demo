package pl.ejdev.reporter.service

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import pl.ejdev.reporter.repository.jsUuid
import pl.ejdev.reporter.templates.HtmlTemplate
import java.io.ByteArrayOutputStream

interface PdfRenderService {
    fun render(code: String): ByteArray
}

class PdfRenderServiceImpl(
    private val templates: Map<HtmlTemplate.Type, HtmlTemplate>,
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

    private fun resolveData(type: HtmlTemplate.Type): List<Any> = when (type) {
        HtmlTemplate.Type.User -> userService.getAll()
        HtmlTemplate.Type.Lesson -> lessonService.getByUserId(jsUuid)
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