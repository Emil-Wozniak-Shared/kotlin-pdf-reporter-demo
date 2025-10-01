package pl.ejdev.reporter.templates

interface HtmlTemplate {
    fun template(data: List<Any>): String

    fun getType(): Type

    enum class Type {
        User
    }
}