package com.challenge.aluraflix.mapper

import com.challenge.aluraflix.dto.VideoViewDto
import com.challenge.aluraflix.model.Video
import org.springframework.stereotype.Component

@Component
class VideoViewMapper: Mapper<Video, VideoViewDto> {
    override fun map(t: Video): VideoViewDto {
        return VideoViewDto(
                id = t.id,
                titulo = t.titulo,
                descricao = t.descricao,
                url = t.url,
                categoriaId = t.categoriaId
        )
    }
}