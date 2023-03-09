package com.challenge.aluraflix.mapper

import com.challenge.aluraflix.dto.VideoFormDto
import com.challenge.aluraflix.model.Video
import org.springframework.stereotype.Component

@Component
class VideoFormMapper: Mapper<VideoFormDto, Video> {
    override fun map(t: VideoFormDto): Video {
        return Video(
                titulo = t.titulo,
                descricao = t.descricao,
                url = t.url
        )
    }
}