package pl.ejdev.reporter.service

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import pl.ejdev.reporter.templates.HtmlTemplate
import java.io.ByteArrayOutputStream

interface PdfRenderService {
    fun render(code: String): ByteArray
}

class PdfRenderServiceImpl(
    private val templates: Map<HtmlTemplate.Type, HtmlTemplate>,
    private val userService: UserService
) : PdfRenderService {

    override fun render(code: String): ByteArray {
        val type = findTypeOrThrow(code)
        val data = getData(type)
        return templates.getValue(type).template(data).let { html -> renderToBytes(html) }
    }

    private fun getData(type: HtmlTemplate.Type): List<Any> = when (type) {
        HtmlTemplate.Type.User -> userService.getAll()
    }

    private fun findTypeOrThrow(code: String): HtmlTemplate.Type =
        HtmlTemplate.Type.entries.find { it.name == code } ?: error("Unknown template $code")

    private fun renderToBytes(html: String) = ByteArrayOutputStream().use { stream ->
        PdfRendererBuilder().run {
            withHtmlContent(html, null)
            toStream(stream)
            run()
        }
        stream
    }.toByteArray()
}