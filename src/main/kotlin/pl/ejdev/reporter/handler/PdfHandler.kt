package pl.ejdev.reporter.handler

import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import pl.ejdev.reporter.service.PdfRenderService
import pl.ejdev.reporter.templates.HtmlTemplate

class PdfHandler(
    private val pdfRenderService: PdfRenderService,
) {
    fun handle(request: ServerRequest): ServerResponse =
        pdfRenderService.render(HtmlTemplate.Type.User.name)
            .let(::ByteArrayResource)
            .let { byteArrayResource ->
                ServerResponse.ok()
                    .headers {
                        it.contentType = MediaType.APPLICATION_PDF
                        it.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.pdf")
                    }
                    .body(byteArrayResource)
            }
}