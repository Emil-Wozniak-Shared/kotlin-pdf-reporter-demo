package pl.ejdev.reporter.templates

import kotlinx.html.*
import kotlinx.html.stream.createHTML

abstract class TemplateData<T: Any>(
    val data: T
)

abstract class HtmlTemplate<TD: TemplateData<T>,  T : Any> {
    abstract fun template(data: TD): String

    abstract val type: Type

    protected fun html(
        title: String,
        block: HTML.() -> Unit
    ) = createHTML().html {
        head {
            title(title)
            withReportStyle()
        }
        block()
    }

    private fun HEAD.withReportStyle() {
        style { +reportStyle }
    }

    enum class Type {
        User, Lesson
    }

    private val reportStyle: String = """
        html {
          font-family:arial;
          font-size: 18px;
        }
        
        td {
          border: 1px solid #726E6D;
          padding: 15px;
        }
        
        thead {
          font-weight:bold;
          text-align:center;
          background: #625D5D;
          color:white;
        }
        
        table {
          border-collapse: collapse;
        }
        
        .footer {
          text-align:right;
          font-weight:bold;
        }
        
        tbody > tr:nth-child(odd) {
          background: #D1D0CE;
        }
        
        table {
          border-collapse: collapse;
          width: 100%;
          page-break-inside: auto;
        }

        tr {
          page-break-inside: avoid;
          page-break-after: auto;
        }

        thead {
          display: table-header-group; /* repeat header on each page */
        }

        tfoot {
          display: table-footer-group; /* repeat footer on each page */
        }
    """.trimIndent()
}