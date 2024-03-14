package com.kchrzanowski.userauth.controller.auth

data class AuthenticationRequest(
    val email: String,
    val password: String
) {

}
