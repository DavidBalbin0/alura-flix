package com.challenge.aluraflix.service

import com.challenge.aluraflix.model.User
import com.challenge.aluraflix.repository.UserRepository

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(private val usuarioRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return usuarioRepository.findByUsername(username)
                ?: throw UsernameNotFoundException("Usuário não encontrado: $username")
    }

}