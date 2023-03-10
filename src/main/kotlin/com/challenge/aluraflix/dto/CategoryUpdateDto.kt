package com.challenge.aluraflix.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CategoryUpdateDto(
        @field:NotNull
        val id: Long,

        @field: NotBlank(message = "O título nao pode estar vazio")
        @field:Size(min = 5, max = 100, message = "O titulo deve ter entre 5 e 100 caracteres")
        val titulo: String,

        @field:NotBlank(message = "A cor nao pode estar vazio")
        @field:Size(min = 5, message = "A cor deve ter mais de 2 caracteres")
        val cor: String
)
