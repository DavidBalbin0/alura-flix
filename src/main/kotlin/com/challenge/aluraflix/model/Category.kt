package com.challenge.aluraflix.model

import com.challenge.aluraflix.dto.CategoryUpdateDto
import com.challenge.aluraflix.dto.VideoUpdateDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
@Entity
data class Category(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var titulo: String,
        var cor: String
){
        fun updateFromDto(dto: CategoryUpdateDto): Category {
                titulo = dto.titulo
                cor = dto.cor

                return this
        }
}
