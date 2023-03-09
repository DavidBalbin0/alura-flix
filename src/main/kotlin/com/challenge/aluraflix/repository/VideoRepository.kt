package com.challenge.aluraflix.repository

import com.challenge.aluraflix.model.Video
import org.springframework.data.jpa.repository.JpaRepository

interface VideoRepository : JpaRepository<Video, Long> {
}