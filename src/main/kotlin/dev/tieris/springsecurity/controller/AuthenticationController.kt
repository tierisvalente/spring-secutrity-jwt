package dev.tieris.springsecurity.controller

import dev.tieris.springsecurity.domain.MessageResponse
import dev.tieris.springsecurity.domain.dto.UserDetail
import dev.tieris.springsecurity.domain.entity.UserEntity
import dev.tieris.springsecurity.domain.request.AuthenticationRequest
import dev.tieris.springsecurity.domain.request.SignupRequest
import dev.tieris.springsecurity.domain.response.AuthenticationResponse
import dev.tieris.springsecurity.service.UserService
import dev.tieris.springsecurity.util.JwtUtil
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userService: UserService
) {

    @PostMapping
    @Throws(Exception::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*>? {
        val responseHeaders = HttpHeaders()
        val authenticate: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authenticationRequest.userName, authenticationRequest.password)
        )

        val userDetails: UserDetail = userService.loadUserByUsername(authenticationRequest.userName) as UserDetail
        val jwt: String = jwtUtil.generateToken(userDetails)
        val roles: MutableList<String> = userDetails.authorities.map { it.authority }.toMutableList()
        val response = AuthenticationResponse(userDetails.getId(), userDetails.username, jwt, roles)

        return ResponseEntity<Any?>(response, responseHeaders, HttpStatus.OK)
    }

    @PostMapping("/signup")
    @ResponseBody
    fun signup(@RequestBody signupRequest: SignupRequest): ResponseEntity<MessageResponse> {

        if(userService.existsByName(signupRequest.name)){
            return ResponseEntity
                .badRequest()
                .body(MessageResponse("Error: Username is already taken!"));
        }

        val user = UserEntity(
            name = signupRequest.name,
            roles = signupRequest.role,
            active = true,
            password = BCryptPasswordEncoder().encode(signupRequest.password)
        )

        userService.save(user)

        return ResponseEntity.ok(MessageResponse("User registered successfully"))
    }
}