package com.radio.shows.service

import com.radio.shows.data.ClientResponse
import com.radio.shows.data.EpisodesResponse
import com.radio.shows.data.Program
import com.radio.shows.data.ProgramsResponse
import com.radio.shows.exception.ResourceNotFoundException
import com.radio.shows.exception.TooManyRequestsException
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter
import java.util.concurrent.*

@Service
class ShowsService(private val restTemplate: RestTemplate) {

//    private val restTemplate: RestTemplate = RestTemplate().apply {
//        messageConverters.add(MappingJackson2XmlHttpMessageConverter())
//    }

    private val showToId: Map<String, Int> = mapOf("p1" to 132, "p2" to 163, "p3" to 164)


    private val semaphore = Semaphore(3)

    fun getResponse(channel: String, show: String): ClientResponse {

        val acquired = semaphore.tryAcquire(1, TimeUnit.SECONDS)
        if (!acquired) {
            throw TooManyRequestsException("Too many requests, please try again later.")
        }

        return try {
            return getEpisodeInfo(channel, show)
        } finally {
            semaphore.release()
        }
    }

    fun getEpisodeInfo(channel: String, show: String): ClientResponse {

        val program = getProgram(channel, show)

        val episodes = program?.let { getLatestEpisodes(it.id) }

        return ClientResponse(program?.name, episodes?.episodes)

    }

    fun getProgram(channel: String, show: String): Program? {
        val channelId = showToId.getValue(channel)

        var programsResponse: ProgramsResponse
        var url = "https://api.sr.se/api/v2/programs/index?channelid=$channelId"
        var nextPage = true

        while (nextPage) {

            programsResponse = restTemplate.getForObject(url, ProgramsResponse::class.java)
                ?: throw Exception("External API fetch from SR failed")

            if (programsResponse.pagination.nextpage !== null) {
                url = programsResponse.pagination.nextpage
                nextPage = true
            } else {
                nextPage = false
            }

            for (program in programsResponse.programs) {
                if (program.name.contains(show, ignoreCase = true)) {
                    nextPage = false
                    return program
                }
            }

        }
        throw ResourceNotFoundException("Show not found")
    }

    fun getLatestEpisodes(programId: Int): EpisodesResponse {
        val url = "https://api.sr.se/api/v2/episodes/index?programid=$programId"

        return restTemplate.getForObject(url, EpisodesResponse::class.java)
            ?: throw Exception("External API fetch from SR failed")
    }

}