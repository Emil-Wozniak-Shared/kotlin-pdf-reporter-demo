package pl.ejdev.reporter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.RouterFunction
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import pl.ejdev.reporter.handler.PdfHandler
import pl.ejdev.reporter.repository.MockUserDao
import pl.ejdev.reporter.repository.UserDao
import pl.ejdev.reporter.service.PdfRenderService
import pl.ejdev.reporter.service.PdfRenderServiceImpl
import pl.ejdev.reporter.service.UserService
import pl.ejdev.reporter.service.UserServiceImpl
import pl.ejdev.reporter.templates.HtmlTemplate
import pl.ejdev.reporter.templates.UserReportHtmlTemplate

@SpringBootApplication
class ReporterApplication

object AppConfiguration {
    val beans = beans {
        bean<UserService> { UserServiceImpl(ref()) }
        bean<UserDao> { MockUserDao() }
        bean<PdfRenderService> { PdfRenderServiceImpl(ref(name = "templates"), ref()) }
        bean<Map<HtmlTemplate.Type, HtmlTemplate>>(name = "templates") {
            mapOf(
                HtmlTemplate.Type.User to UserReportHtmlTemplate
            )
        }
        bean<PdfHandler> { PdfHandler(ref()) }
        bean<RouterFunction<ServerResponse>> {
            val pdfHandler = ref<PdfHandler>()
            router {
                GET("/api/report", pdfHandler::handle)
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ReporterApplication>(*args) {
        addInitializers(AppConfiguration.beans)
    }
}

