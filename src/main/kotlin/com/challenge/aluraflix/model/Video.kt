package com.challenge.aluraflix.model

import com.challenge.aluraflix.dto.VideoUpdateDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.Getter

@Entity
data class Video(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var titulo: String,
        var descricao: String,
        var url: String
){
        fun updateFromDto(dto: VideoUpdateDto): Video {
                titulo = dto.titulo ?: titulo
                descricao = dto.descricao ?: descricao
                url = dto.url ?: url

                return this
        }
}
