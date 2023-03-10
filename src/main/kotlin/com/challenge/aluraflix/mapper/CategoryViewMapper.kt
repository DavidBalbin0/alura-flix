package com.challenge.aluraflix.mapper

import com.challenge.aluraflix.dto.CategoryViewDto
import com.challenge.aluraflix.model.Category
import org.springframework.stereotype.Component

@Component
class CategoryViewMapper : Mapper<Category, CategoryViewDto> {
    override fun map(c: Category): CategoryViewDto {
        return CategoryViewDto(
                id = c.id,
                titulo = c.titulo,
                cor = c.cor
        )
    }
}