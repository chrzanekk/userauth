package com.kchrzanowski.userauth.service.impl

import com.kchrzanowski.userauth.config.JwtProperties
import com.kchrzanowski.userauth.controller.auth.AuthenticationRequest
import com.kchrzanowski.userauth.controller.auth.AuthenticationResponse
import com.kchrzanowski.userauth.controller.auth.RefreshTokenRequest
import com.kchrzanowski.userauth.repository.RefreshTokenRepository
import com.kchrzanowski.userauth.service.AuthenticationService
import com.kchrzanowski.userauth.service.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationServiceImpl(
    private val authManager: AuthenticationManager,
    private val userDetailsService: UserDetailsServiceImpl,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) : AuthenticationService {

    override fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.email,
                authenticationRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authenticationRequest.email)

        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user)
        refreshTokenRepository.save(refreshToken, user)

        return AuthenticationResponse(accessToken, refreshToken)
    }

    private fun generateRefreshToken(user: UserDetails) = tokenService.generate(
        user,
        Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
    )

    private fun generateAccessToken(user: UserDetails) = tokenService.generate(
            user,
            Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
        )


    override fun refreshAccessToken(refreshTokenRequest: RefreshTokenRequest): String? {
        val extractedEmail = tokenService.extractEmail(refreshTokenRequest.token)

        return extractedEmail?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)
            val findAllTokens = refreshTokenRepository.findAll()
            val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(refreshTokenRequest.token)
            if (!tokenService.isExpired(refreshTokenRequest.token) && currentUserDetails.username == refreshTokenUserDetails?.username)
                generateAccessToken(currentUserDetails)
            else null
        }
    }
}


