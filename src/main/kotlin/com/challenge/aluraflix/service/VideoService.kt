package com.challenge.aluraflix.service

import com.challenge.aluraflix.dto.VideoFormDto
import com.challenge.aluraflix.dto.VideoUpdateDto
import com.challenge.aluraflix.dto.VideoViewDto
import com.challenge.aluraflix.exception.NotFoundException
import com.challenge.aluraflix.mapper.VideoFormMapper
import com.challenge.aluraflix.mapper.VideoViewMapper
import com.challenge.aluraflix.model.Video
import com.challenge.aluraflix.repository.VideoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class VideoService (
        private val videoRepository: VideoRepository,
        private val videoFormMapper: VideoFormMapper,
        private val videoViewMapper: VideoViewMapper,
        private val categoryService: CategoryService,
        private val notFoundMessage: String = "Video nao encontrado"
        ) {

    fun getAll(
            title: String?,
            pageable: Pageable): ResponseEntity<Page<VideoViewDto>> {
        println(title)
        val videos = if (!title.isNullOrBlank()) {
            videoRepository.findAllByTituloContainingIgnoreCase(title, pageable)
        } else {
            videoRepository.findAll(pageable)
        }
        return ResponseEntity.ok(videos.map { videoViewMapper.map(it) })
    }

    fun getBtId(id: Long): ResponseEntity<VideoViewDto> {
        val video = videoRepository.findById(id)
                .orElseThrow { NotFoundException(notFoundMessage) }
        return ResponseEntity.ok(videoViewMapper.map(video))

    }

    fun post(form: VideoFormDto, uriBuilder: UriComponentsBuilder): ResponseEntity<VideoViewDto>{
        categoryService.verifyCategoryId(form.categoriaId)
        val video = videoFormMapper.map(form)
        videoRepository.save(video)
        val videoView = videoViewMapper.map(video)

        val uri = uriBuilder.path("/getbyId/${videoView}").build().toUri()
        return ResponseEntity.created(uri).body(videoView)
    }

    fun put(form: VideoUpdateDto): ResponseEntity<VideoViewDto>{
        val video = videoRepository.findById(form.id).orElseThrow{ NotFoundException(notFoundMessage) }
        video.updateFromDto(form)

        val updateVideo = videoRepository.saveAndFlush(video)

        return ResponseEntity.ok(videoViewMapper.map(updateVideo))
    }

    fun delete(id: Long){
        if(videoRepository.existsById(id)) videoRepository.deleteById(id)
            else throw NotFoundException(notFoundMessage)
    }

    fun assignCategoryLivre(): List<VideoViewDto> {
        val videos = videoRepository.findAllByCategoriaIdIsNull()
        videos.map { v -> v.categoriaId = 1 }
        videoRepository.saveAll(videos)
        return videos.map { v -> videoViewMapper.map(v) }
    }

    fun getVideosByCategoryId(id: Long): List<VideoViewDto> {
        val videos = videoRepository.findAllByCategoriaId(id)
        return videos.map { v -> videoViewMapper.map(v) }
    }

}

