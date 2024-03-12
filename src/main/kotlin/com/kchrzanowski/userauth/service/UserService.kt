package com.kchrzanowski.userauth.service

import com.kchrzanowski.userauth.controller.user.UserRequest
import com.kchrzanowski.userauth.controller.user.UserResponse
import com.kchrzanowski.userauth.model.User
import java.util.*

interface UserService {

    fun create(user: UserRequest): UserResponse
    fun findByEmail(email: String): UserResponse
    fun findAll(): List<UserResponse>
    fun findByUUID(uuid: UUID): UserResponse
    fun deleteByUUID(uuid: UUID): Boolean
}