package com.challenge.aluraflix.controller

import com.challenge.aluraflix.dto.AuthData
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController(
        private val mananger: AuthenticationManager
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid user: AuthData): ResponseEntity<Void>{
        println(BCrypt.checkpw(user.password, "\$2y\$10\$j3WptTr.2/53aorJDf4owuk0A/W6Z.2lzCeydPCASn2JVGchMPECa"))
        val token = UsernamePasswordAuthenticationToken(user.login, user.password)
        var authentication = mananger.authenticate(token)

        return ResponseEntity.ok().build()
    }
}