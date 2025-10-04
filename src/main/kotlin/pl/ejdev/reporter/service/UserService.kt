package pl.ejdev.reporter.service

import pl.ejdev.reporter.model.User
import pl.ejdev.reporter.repository.UserDao

interface UserService {
    fun getAll(): List<User>
}

class UserServiceImpl(
    private val userDao: UserDao
): UserService {
    override fun getAll(): List<User> = userDao.getAll()
}