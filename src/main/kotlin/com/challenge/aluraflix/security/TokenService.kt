package com.challenge.aluraflix.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit


@Service
class TokenService(
        @Value("\${aluraflix_secret}")
        private val secret: String
) {



    fun genToken(username: String): String{
        println(secret)
        try {
            val algorithm: Algorithm = Algorithm.HMAC256(secret)
            return JWT.create()
                    .withIssuer("AluraFlix")
                    .withSubject(username)
                    .withExpiresAt(tenMinutesFromNow())
                    .sign(algorithm)
        } catch (exception: JWTCreationException) {
        throw RuntimeException("Erro ao gerar o token jwt: $exception")
        }
    }

    fun getSubject(tokenJwt: String): String{
        try {
            val algorithm: Algorithm = Algorithm.HMAC256(secret)
            return JWT.require(algorithm) // specify an specific claim validations
                    .withIssuer("AluraFlix") // reusable verifier instance
                    .build()
                    .verify(tokenJwt)
                    .subject

        } catch (exception: JWTVerificationException) {
            throw RuntimeException("Token JWT invalido ou expirado")
        }
    }

    fun tenMinutesFromNow(): Instant {
        return Instant.now().plus(10, ChronoUnit.MINUTES)
    }
}