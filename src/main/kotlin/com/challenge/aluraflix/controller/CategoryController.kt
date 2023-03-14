package com.challenge.aluraflix.controller

import com.challenge.aluraflix.dto.*
import com.challenge.aluraflix.service.CategoryService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/category")
@SecurityRequirement(name = "bearer-key")
class CategoryController(
        private val categoryService: CategoryService
) {

    @GetMapping("/getall")
    fun getAll(
            @RequestParam(name = "categoria", required = false) nameCategory: String?,
            @PageableDefault(size = 5, sort = ["id"]) pageable: Pageable
    ): Page<CategoryViewDto> {
        return categoryService.getAll(nameCategory, pageable)
    }
    @GetMapping("/getById/{id}")
    fun getVideoById(@PathVariable id: Long): CategoryViewDto {
        return categoryService.getBtId(id)
    }
    @GetMapping("{id}/videos")
    fun getVideosByCategoryId(@PathVariable id: Long): List<VideoViewDto> {
        return categoryService.getVideosByCategoryId(id)
    }
    @PostMapping("/post")
    fun postVideo(@Valid @RequestBody form: CategoryFormDto, uriBuilder: UriComponentsBuilder): ResponseEntity<CategoryViewDto>{
        return categoryService.post(form, uriBuilder)
    }

    @PutMapping("/put")
    fun putVideo(@RequestBody @Valid form: CategoryUpdateDto): ResponseEntity<CategoryViewDto>{
        return categoryService.put(form)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long){
        categoryService.delete(id)
    }
}