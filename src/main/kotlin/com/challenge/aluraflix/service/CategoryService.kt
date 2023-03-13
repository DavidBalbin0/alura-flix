package com.challenge.aluraflix.service

import com.challenge.aluraflix.dto.*
import com.challenge.aluraflix.exception.NotFoundException
import com.challenge.aluraflix.mapper.CategoryFormMapper
import com.challenge.aluraflix.mapper.CategoryViewMapper
import com.challenge.aluraflix.model.Category
import com.challenge.aluraflix.repository.CategoryRepository
import org.springframework.context.annotation.Lazy
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val categoryViewMapper: CategoryViewMapper,
        private val categoryFormMapper: CategoryFormMapper,
        @Lazy
        private val videoService: VideoService,
        private val notFoundMessage: String = "Categoria nao encontrada!",

) {

    fun getAll(nameCategory: String?, pageable: Pageable): Page<CategoryViewDto> {
        val category = if(!nameCategory.isNullOrBlank()) {
            categoryRepository.findAllByTituloContainingIgnoreCase(nameCategory, pageable)
        } else {
            categoryRepository.findAll(pageable)
        }
        return category.map { c -> categoryViewMapper.map(c) }
    }

    fun getBtId(id: Long): CategoryViewDto {
        return categoryRepository.findById(id)
                .map { categoryViewMapper.map(it) }
                .orElseThrow { NotFoundException(notFoundMessage) }
    }

    fun post(form: CategoryFormDto, uriBuilder: UriComponentsBuilder): ResponseEntity<CategoryViewDto>{
        val category = categoryFormMapper.map(form)
        categoryRepository.save(category)
        val categoryView = categoryViewMapper.map(category)

        val uri = uriBuilder.path("/getbyId/${categoryView}").build().toUri()
        return ResponseEntity.created(uri).body(categoryView)
    }

    fun put(form: CategoryUpdateDto): ResponseEntity<CategoryViewDto>{
        val video = categoryRepository.findById(form.id).orElseThrow{ NotFoundException(notFoundMessage) }
        video.updateFromDto(form)

        val updateVideo = categoryRepository.saveAndFlush(video)

        return ResponseEntity.ok(categoryViewMapper.map(updateVideo))
    }

    fun delete(id: Long){
        if(categoryRepository.existsById(id)) categoryRepository.deleteById(id)
        else throw NotFoundException(notFoundMessage)
    }

    fun verifyCategoryId(id: Long) {
        categoryRepository.findById(id)
                .orElseThrow{NotFoundException(notFoundMessage)}
    }

    fun getVideosByCategoryId(id: Long):  List<VideoViewDto>  {
        verifyCategoryId(id)
        return videoService.getVideosByCategoryId(id)
    }

}