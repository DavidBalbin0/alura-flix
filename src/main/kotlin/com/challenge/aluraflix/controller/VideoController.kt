package com.challenge.aluraflix.controller

import com.challenge.aluraflix.dto.VideoFormDto
import com.challenge.aluraflix.dto.VideoViewDto
import com.challenge.aluraflix.model.Video
import com.challenge.aluraflix.service.VideoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RequestMapping("/video")
@RestController
class VideoController(private val videoService: VideoService) {

    @GetMapping("/getall")
    fun getVideos(): List<VideoViewDto>{
        return videoService.getAll()
    }
    @GetMapping("/getById/{id}")
    fun getVideoById(@PathVariable id: Long): Video{
        return videoService.getBtId(id)
    }
    @PostMapping("/post")
    fun postVideo(@RequestBody @Valid form: VideoFormDto, uriBuilder: UriComponentsBuilder): ResponseEntity<VideoViewDto>{
        println("chegou aqui")
        return videoService.post(form, uriBuilder)
    }
}