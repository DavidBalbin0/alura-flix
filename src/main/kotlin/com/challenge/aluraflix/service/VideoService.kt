package com.challenge.aluraflix.service

import com.challenge.aluraflix.dto.VideoFormDto
import com.challenge.aluraflix.dto.VideoUpdateDto
import com.challenge.aluraflix.dto.VideoViewDto
import com.challenge.aluraflix.exception.NotFoundException
import com.challenge.aluraflix.mapper.VideoFormMapper
import com.challenge.aluraflix.mapper.VideoWiewMapper
import com.challenge.aluraflix.model.Video
import com.challenge.aluraflix.repository.VideoRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.util.UriComponentsBuilder

@Service
class VideoService (
        private val videoRepository: VideoRepository,
        private val videoFormMapper: VideoFormMapper,
        private val videoViewMapper: VideoWiewMapper,
        private val notFoundMessage: String = "Video nao encontrado"
        ) {

    fun getAll(): List<VideoViewDto> {
        val videos = videoRepository.findAll()

        return videos.map { v -> videoViewMapper.map(v) }
    }

    fun getBtId(id: Long): Video {
        return videoRepository.findById(id)
                .orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun post(form: VideoFormDto, uriBuilder: UriComponentsBuilder): ResponseEntity<VideoViewDto>{
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

}

