package com.challenge.aluraflix.mapper

import com.challenge.aluraflix.dto.CategoryFormDto
import com.challenge.aluraflix.model.Category
import org.springframework.stereotype.Component

@Component
class CategoryFormMapper : Mapper<CategoryFormDto, Category> {

    override fun map(c: CategoryFormDto): Category {
        return Category(
                titulo = c.titulo,
                cor = c.cor
        )
    }
}