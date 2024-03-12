package com.kchrzanowski.userauth.model

import com.kchrzanowski.userauth.enumerated.Role
import java.util.UUID

//todo extend role filed to multiple roles
data class User(
        val id: UUID,
        val email: String,
        val password: String,
        val role: Role,
)
