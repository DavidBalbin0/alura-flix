package com.challenge.aluraflix

import com.challenge.aluraflix.dto.CategoryUpdateDto
import com.challenge.aluraflix.model.Category
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CategoryTest {
    @Test
    fun `updateFromDto should update the category fields`() {
        // given
        val category = Category(
                titulo = "Old Title",
                cor = "#000000"
        )
        val dto = CategoryUpdateDto(
                id = 1,
                titulo = "New Title",
                cor = "#FFFFFF"
        )

        // when
        val result = category.updateFromDto(dto)

        // then
        assertEquals("New Title", result.titulo)
        assertEquals("#FFFFFF", result.cor)
    }
}