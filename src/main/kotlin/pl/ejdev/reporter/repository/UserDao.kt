package pl.ejdev.reporter.repository

import pl.ejdev.reporter.model.UserDto

interface UserDao {
    fun getAll(): List<UserDto>
}

class MockUserDao: UserDao {
    private val store = listOf(
        UserDto("Emil", "Woźniak", "xxxxxx"),
        UserDto("John", "Snow", "xxxxxx"),
        UserDto("John", "Smith", "xxxxxx"),
    )
    override fun getAll(): List<UserDto> = store.toList()
}