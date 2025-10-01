package pl.ejdev.reporter.service

import pl.ejdev.reporter.model.UserDto
import pl.ejdev.reporter.repository.UserDao

interface UserService {
    fun getAll(): List<UserDto>
}

class UserServiceImpl(
    private val userDao: UserDao
): UserService {
    override fun getAll(): List<UserDto> = userDao.getAll()
}