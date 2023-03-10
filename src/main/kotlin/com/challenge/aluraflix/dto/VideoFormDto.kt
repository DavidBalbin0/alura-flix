package com.challenge.aluraflix.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class VideoFormDto(

        @field:NotBlank(message = "O título nao pode estar vazio")
        @field:NotEmpty
        @field:Size(min = 5, max = 100, message = "O titulo deve ter entre 5 e 100 caracteres")
        val titulo: String,

        @field:NotBlank(message = "A descrição nao pode estar vazio")
        @field:Size(min = 5, message = "A descrição deve ter mais de 5 caracteres")
        val descricao: String,

        @field:NotBlank(message = "A url nao pode estar vazio")
        @field:Size(min = 5, message = "A url deve ter mais de 5 caracteres")
        val url: String
){

}
