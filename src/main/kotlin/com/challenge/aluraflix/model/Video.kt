package com.challenge.aluraflix.model

import com.challenge.aluraflix.dto.VideoUpdateDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Getter

@Entity
@Table(name = "video")
data class Video(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var titulo: String,
        var descricao: String,
        var url: String,
        @Column(name = "categoria_id")
        var categoriaId: Long? = 1
){
        fun updateFromDto(dto: VideoUpdateDto): Video {
                titulo = dto.titulo
                descricao = dto.descricao
                url = dto.url
                categoriaId = dto.categoriaId

                return this
        }
}
