package com.challenge.aluraflix.repository

import com.challenge.aluraflix.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
    fun findAllByTituloContainingIgnoreCase(nameCategory: String, pageable: Pageable): Page<Category>
}