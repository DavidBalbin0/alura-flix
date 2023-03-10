package com.challenge.aluraflix.repository

import com.challenge.aluraflix.model.Video
import org.hibernate.exception.spi.ViolatedConstraintNameExtractor
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : JpaRepository<Video, Long> {
    fun findAllByCategoriaIdIsNull(): List<Video>
    fun findAllByCategoriaId(id: Long): List<Video>

    fun findAllByTituloContainingIgnoreCase(titulo: String): Page<Video>
}