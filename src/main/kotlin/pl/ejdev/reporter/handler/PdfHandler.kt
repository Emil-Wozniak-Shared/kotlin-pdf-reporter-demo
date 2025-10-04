package pl.ejdev.reporter.handler

import org.springframework.core.io.ByteArrayResource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import pl.ejdev.reporter.service.PdfRenderService
import pl.ejdev.reporter.templates.HtmlTemplate

class PdfHandler(
    private val pdfRenderService: PdfRenderService,
) {
    fun handleUser(request: ServerRequest): ServerResponse = handle(HtmlTemplate.Type.User)

    fun handleLesson(request: ServerRequest): ServerResponse = handle(HtmlTemplate.Type.Lesson)

    private fun handle(type: HtmlTemplate.Type): ServerResponse =
        pdfRenderService.render(type.name)
            .let(::ByteArrayResource)
            .let { ServerResponse.ok().contentDisposition(it) }

    private fun ServerResponse.BodyBuilder.contentDisposition(byteArrayResource: ByteArrayResource) =
        this.headers {
            it.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.pdf")
            it.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
        }.body(byteArrayResource)
}