package com.challenge.aluraflix.controller

import com.challenge.aluraflix.dto.VideoFormDto
import com.challenge.aluraflix.dto.VideoUpdateDto
import com.challenge.aluraflix.dto.VideoViewDto
import com.challenge.aluraflix.model.Video
import com.challenge.aluraflix.service.VideoService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

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
    fun postVideo(@Valid @RequestBody form: VideoFormDto, uriBuilder: UriComponentsBuilder): ResponseEntity<VideoViewDto>{
        return videoService.post(form, uriBuilder)
    }

    @PutMapping("/put")
    fun putVideo(@RequestBody @Valid form: VideoUpdateDto): ResponseEntity<VideoViewDto>{
        return videoService.put(form)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long){
        videoService.delete(id)
    }
}