package com.challenge.aluraflix.mapper

interface Mapper<T,U> {

    fun map(t : T): U
}