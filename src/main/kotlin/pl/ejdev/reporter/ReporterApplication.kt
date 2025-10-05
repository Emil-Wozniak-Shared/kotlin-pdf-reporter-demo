package pl.ejdev.reporter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.RouterFunction
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import pl.ejdev.reporter.handler.PdfHandler
import pl.ejdev.reporter.repository.LessonDao
import pl.ejdev.reporter.repository.MockLessonDao
import pl.ejdev.reporter.repository.MockUserDao
import pl.ejdev.reporter.repository.UserDao
import pl.ejdev.reporter.service.*
import pl.ejdev.reporter.templates.HtmlTemplate
import pl.ejdev.reporter.templates.LessonReportHtmlTemplate
import pl.ejdev.reporter.templates.TemplateData
import pl.ejdev.reporter.templates.UserReportHtmlTemplate

@SpringBootApplication
class ReporterApplication

private const val TEMPLATES_QUALIFIER = "templates"

object AppConfiguration {
    val beans = beans {
        bean<UserService> { UserServiceImpl(ref()) }
        bean<LessonService> { LessonServiceImpl(ref()) }
        bean<UserDao> { MockUserDao() }
        bean<LessonDao> { MockLessonDao() }
        bean<PdfRenderService> { PdfRenderServiceImpl(ref(name = TEMPLATES_QUALIFIER), ref(), ref()) }
        bean<Map<HtmlTemplate.Type, HtmlTemplate<out TemplateData<*>, *>>>(name = TEMPLATES_QUALIFIER) {
            mapOf(
                UserReportHtmlTemplate.type to UserReportHtmlTemplate,
                LessonReportHtmlTemplate.type to LessonReportHtmlTemplate,
            )
        }
        bean<PdfHandler> { PdfHandler(ref()) }
        bean<RouterFunction<ServerResponse>> {
            val pdfHandler = ref<PdfHandler>()
            router {
                "/api".nest {
                    "/report".nest {
                        GET("/user", pdfHandler::handleUser)
                        GET("/lesson", pdfHandler::handleLesson)
                    }
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ReporterApplication>(*args) {
        addInitializers(AppConfiguration.beans)
    }
}

