package com.challenge.aluraflix.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        private var username: String,
        private var password: String,

) : UserDetails {
        override fun getAuthorities(): List<GrantedAuthority> {
                return listOf(SimpleGrantedAuthority("ROLE_USER"))
        }

        override fun getPassword(): String {
                return password
        }

        override fun getUsername(): String {
                return username
        }

        override fun isAccountNonExpired(): Boolean {
                return true
        }

        override fun isAccountNonLocked(): Boolean {
                return true
        }

        override fun isCredentialsNonExpired(): Boolean {
                return true
        }

        override fun isEnabled(): Boolean {
                return true
        }
}
