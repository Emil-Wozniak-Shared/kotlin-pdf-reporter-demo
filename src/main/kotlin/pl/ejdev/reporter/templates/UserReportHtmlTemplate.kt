package pl.ejdev.reporter.templates

import kotlinx.html.*
import pl.ejdev.reporter.model.User

private const val TITLE = "Student Report Card"
private const val FIRST_NAME = "First name"
private const val LAST_NAME = "Last name"
private const val ID = "ID"

object UserReportHtmlTemplate : HtmlTemplate() {
    override val type: Type = Type.User

    @Suppress("UNCHECKED_CAST")
    override fun template(data: List<Any>): String = html(TITLE) {
        val users = data as List<User>
        body {
            div {
                h2 { +TITLE }
                table {
                    thead {
                        tr {
                            th(ThScope.col) { +ID }
                            th(ThScope.col) { +FIRST_NAME }
                            th(ThScope.col) { +LAST_NAME }
                        }
                    }
                    tbody { usersRows(users) }
                }
            }
        }
    }

    private fun TBODY.usersRows(users: List<User>) {
        users.forEach { user ->
            tr {
                td { +user.id.toString() }
                td { +user.firstName }
                td { +user.lastName }
            }
        }
    }
}