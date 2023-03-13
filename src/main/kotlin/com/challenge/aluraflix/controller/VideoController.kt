package com.challenge.aluraflix.controller

import com.challenge.aluraflix.dto.VideoFormDto
import com.challenge.aluraflix.dto.VideoUpdateDto
import com.challenge.aluraflix.dto.VideoViewDto
import com.challenge.aluraflix.model.Video
import com.challenge.aluraflix.service.VideoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RequestMapping("/video")
@RestController
class VideoController(private val videoService: VideoService) {

    @GetMapping("/getall")
    fun getVideos(
            @RequestParam(required = false, name = "titulo") title: String?,
            @PageableDefault(size = 5, sort = ["id"]) pageable: Pageable):
            ResponseEntity<Page<VideoViewDto>>{
        return videoService.getAll(title, pageable)
    }
    @GetMapping("/getById/{id}")
    fun getVideoById(@PathVariable id: Long): ResponseEntity<VideoViewDto>{
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
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        videoService.delete(id)
        return ResponseEntity.noContent().build()
    }
    @GetMapping("/assigncategory")
    fun assignCategoryLivreToVideo(): List<VideoViewDto>{
        return videoService.assignCategoryLivre()
    }
}