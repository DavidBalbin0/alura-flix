package com.challenge.aluraflix

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
class AluraflixApplication

fun main(args: Array<String>) {
	runApplication<AluraflixApplication>(*args)
}
