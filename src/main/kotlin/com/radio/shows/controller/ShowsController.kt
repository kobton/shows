package com.radio.shows.controller

import com.radio.shows.data.ClientResponse
import com.radio.shows.service.ShowsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api")
@RestController
class ApiController(private val showsService: ShowsService) {


    @GetMapping("/{channel}")
    fun fetchApi(@PathVariable channel: String, @RequestParam show: String): ClientResponse {
        return showsService.getResponse(channel, show)
    }
}