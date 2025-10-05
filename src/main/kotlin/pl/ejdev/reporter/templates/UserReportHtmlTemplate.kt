package pl.ejdev.reporter.templates

import kotlinx.html.*
import pl.ejdev.reporter.model.User

private const val TITLE = "Student Report Card"
private const val FIRST_NAME = "First name"
private const val LAST_NAME = "Last name"
private const val ID = "ID"

class UsersTemplateData(
    val users: List<User>
) : TemplateData<List<User>>(users)

object UserReportHtmlTemplate : HtmlTemplate<UsersTemplateData, List<User>>() {
    override val type: Type = Type.User

    override fun template(data: UsersTemplateData): String = html(TITLE) {
        val users = data.users
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