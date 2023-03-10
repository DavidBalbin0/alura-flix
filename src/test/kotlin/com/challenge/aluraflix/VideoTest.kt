package com.challenge.aluraflix

import com.challenge.aluraflix.dto.VideoUpdateDto
import com.challenge.aluraflix.model.Video
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VideoTest {
    @Test
    fun `updateFromDto should update the video fields`() {
        // given
        val video = Video(
                titulo = "Old Title",
                descricao = "Old Description",
                url = "Old URL",
                categoriaId = 1
        )
        val dto = VideoUpdateDto(
                id = 1,
                titulo = "New Title",
                descricao = "New Description",
                url = "New URL",
                categoriaId = 2L
        )

        // when
        val result = video.updateFromDto(dto)

        // then
        assertEquals("New Title", result.titulo)
        assertEquals("New Description", result.descricao)
        assertEquals("New URL", result.url)
        assertEquals(2, result.categoriaId)
    }
}