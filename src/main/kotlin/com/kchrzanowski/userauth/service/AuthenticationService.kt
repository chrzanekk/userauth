package com.kchrzanowski.userauth.service

import com.kchrzanowski.userauth.controller.auth.AuthenticationRequest
import com.kchrzanowski.userauth.controller.auth.AuthenticationResponse
import com.kchrzanowski.userauth.controller.auth.RefreshTokenRequest
import com.kchrzanowski.userauth.controller.auth.TokenResponse

interface AuthenticationService {

    fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse

    fun refreshAccessToken(refreshTokenRequest: RefreshTokenRequest): String?

}
