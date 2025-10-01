package pl.ejdev.reporter.templates

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import pl.ejdev.reporter.model.UserDto

object UserReportHtmlTemplate : HtmlTemplate {
    override fun getType(): HtmlTemplate.Type = HtmlTemplate.Type.User

    @Suppress("UNCHECKED_CAST")
    override fun template(data: List<Any>): String = createHTML().html {
        val users = data as List<UserDto>
        head {
            style {
                unsafe {
                    +rawCss()
                }
            }
        }
        body {
            div {
                lang = "en/GB"
                h2 { +"Users:" }
                table {
                    tr {
                        th { +"Firt name" }
                        th { +"Last name" }
                        th { +"PESEL" }
                    }
                    users.forEach { user ->
                        tr {
                            td { +user.firstName }
                            td { +user.lastName }
                            td { +user.pesel }
                        }
                    }
                }
            }
        }
    }

    private fun rawCss(): String = """
	table {
	   font-family: Arial, Helvetica, sans-serif;
	   border-collapse: collapse;
	   width: 100%;
	 }
	 
	td, th {
	   border: 1px solid #ddd;
	   padding: 8px;
	}
	 
	tr:nth-child(even){background-color: #f2f2f2;}
	 
	tr:hover {background-color: #ddd;}
	 
	th {
	   padding-top: 12px;
	   padding-bottom: 12px;
	   text-align: left;
	   background-color: #04AA6D;
	   color: white;
	}
    """.trimIndent()
}