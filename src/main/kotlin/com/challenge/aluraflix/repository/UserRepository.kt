package com.challenge.aluraflix.repository

import com.challenge.aluraflix.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User,Long> {
    fun findByUsername(username: String): User
}