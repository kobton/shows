package com.radio.shows

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShowsApplication

fun main(args: Array<String>) {
	runApplication<ShowsApplication>(*args)
}
