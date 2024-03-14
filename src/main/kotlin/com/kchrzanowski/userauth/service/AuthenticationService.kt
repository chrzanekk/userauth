package com.kchrzanowski.userauth.service

import com.kchrzanowski.userauth.controller.auth.AuthenticationRequest
import com.kchrzanowski.userauth.controller.auth.AuthenticationResponse

interface AuthenticationService {

    fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse

}
