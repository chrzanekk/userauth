package com.kchrzanowski.userauth.repository

import com.kchrzanowski.userauth.enumerated.Role
import com.kchrzanowski.userauth.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*

//todo again extend to connect to real DB

@Repository
class UserRepository(
    private val encoder: PasswordEncoder
) {

    private val users = mutableListOf(
            User(id = UUID.randomUUID(), "email1@1email.com", encoder.encode("pass1"), Role.USER),
            User(id = UUID.randomUUID(), "email2@1email.com", encoder.encode("pass2"), Role.ADMIN),
            User(id = UUID.randomUUID(), "email3@1email.com", encoder.encode("pass3"), Role.USER),
    )

    fun save(user: User): Boolean {
        val updated = user.copy(password = encoder.encode(user.password))
        return users.add(updated)
    }

    fun findByEmail(email: String): User? =
            users.firstOrNull { it.email == email }

    fun findAll() :List<User> = users

    fun findByUUID(uuid: UUID): User? =
            users.firstOrNull { it.id == uuid }

    fun deleteByUUID(uuid: UUID): Boolean {
        val fountUser = findByUUID(uuid)
        return fountUser?.let {
            users.remove(it)
        } ?: false
    }
}