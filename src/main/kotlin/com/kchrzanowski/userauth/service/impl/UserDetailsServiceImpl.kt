package com.kchrzanowski.userauth.service.impl

import com.kchrzanowski.userauth.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = com.kchrzanowski.userauth.model.User
@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) :UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails = userRepository.findByEmail(username)
        ?.mapToUserDetails()
        ?: throw UsernameNotFoundException("User not found")


    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()
}