package com.kchrzanowski.userauth.service.impl

import com.kchrzanowski.userauth.controller.user.UserRequest
import com.kchrzanowski.userauth.controller.user.UserResponse
import com.kchrzanowski.userauth.enumerated.Role
import com.kchrzanowski.userauth.model.User
import com.kchrzanowski.userauth.repository.UserRepository
import com.kchrzanowski.userauth.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserServiceImpl(
        private val userRepository: UserRepository
) : UserService {
    override fun create(user: UserRequest): UserResponse {
        val foundUser = userRepository.findByEmail(user.email)

        if (foundUser == null) {
            val toSave = User(UUID.randomUUID(), user.email, user.password, Role.USER)
            val saved = userRepository.save(toSave)
            return toSave.toResponse()
        } else throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create user")
    }

    override fun findByEmail(email: String): UserResponse = userRepository.findByEmail(email)?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot found user")

    override fun findAll(): List<UserResponse> = userRepository.findAll().map { it.toResponse() }

    override fun findByUUID(uuid: UUID): UserResponse = userRepository.findByUUID(uuid)?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot found user")

    override fun deleteByUUID(uuid: UUID): Boolean {
        val success = userRepository.deleteByUUID(uuid)
        return if (!success) throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot found user")
        else true
    }


    fun User.toResponse(): UserResponse =
            UserResponse(
                    id = this.id,
                    email = this.email
            )
}