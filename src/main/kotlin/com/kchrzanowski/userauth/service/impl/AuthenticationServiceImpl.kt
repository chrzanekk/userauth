package com.kchrzanowski.userauth.service.impl

import com.kchrzanowski.userauth.config.JwtProperties
import com.kchrzanowski.userauth.controller.auth.AuthenticationRequest
import com.kchrzanowski.userauth.controller.auth.AuthenticationResponse
import com.kchrzanowski.userauth.service.AuthenticationService
import com.kchrzanowski.userauth.service.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationServiceImpl(
    private val authManager: AuthenticationManager,
    private val userDetailsService: UserDetailsServiceImpl,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
) : AuthenticationService {

    override fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.email,
                authenticationRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authenticationRequest.email)
        val accessToken = tokenService.generate(
            user,
            Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )

        return AuthenticationResponse(accessToken)
    }
}