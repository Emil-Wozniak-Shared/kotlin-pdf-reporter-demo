package pl.ejdev.reporter.repository

import pl.ejdev.reporter.model.User
import java.util.UUID

interface UserDao {
    fun getAll(): List<User>
}

val jsUuid: UUID = UUID.fromString("1e9bbad5-7b73-41e4-a5b8-87c8a321ced8")

class MockUserDao: UserDao {
    private val store = listOf(
        User(jsUuid, "John", "Smith")
    )
    override fun getAll(): List<User> = store.toList()
}