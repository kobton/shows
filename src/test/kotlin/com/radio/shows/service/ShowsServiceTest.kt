package com.radio.shows.service

import com.radio.shows.data.ClientResponse
import com.radio.shows.data.Episode
import com.radio.shows.data.EpisodesResponse
import com.radio.shows.data.Pagination
import com.radio.shows.data.Program
import com.radio.shows.data.ProgramsResponse
import com.radio.shows.exception.ResourceNotFoundException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

class ShowsServiceTest {

    private lateinit var mockRestTemplate: RestTemplate;
    private lateinit var service: ShowsService

    private lateinit var pagination: Pagination
    private lateinit var program: Program
    private lateinit var programs: List<Program>
    private lateinit var programsResponse: ProgramsResponse

    private lateinit var episode: Episode
    private lateinit var episodes: List<Episode>
    private lateinit var episodesResponse: EpisodesResponse

    private lateinit var clientResponse: ClientResponse



    @BeforeEach
    fun setup() {
        mockRestTemplate = mock(RestTemplate::class.java)
        service = ShowsService(mockRestTemplate)

        pagination = Pagination(null)
        program = Program(1, "test program" )
        programs = listOf(program)
        programsResponse = ProgramsResponse()
        programsResponse.pagination = pagination
        programsResponse.programs = programs

        episode = Episode("Test episode", LocalDate.now(), "test description")
        episodes = listOf(episode)
        episodesResponse = EpisodesResponse()
        episodesResponse.pagination = pagination
        episodesResponse.episodes = episodes

        clientResponse = ClientResponse("test response", episodes)

    }

    @Test
    fun getProgram_not_found() {
        var url = "https://api.sr.se/api/v2/programs/index?channelid=132"

        `when`(mockRestTemplate.getForObject(url, ProgramsResponse::class.java)).thenReturn(programsResponse)

        val exception = assertThrows<ResourceNotFoundException> {  service.getProgram("p1", "not available")}

        assertEquals("Show not found", exception.message)
        verify(mockRestTemplate).getForObject(url, ProgramsResponse::class.java)
    }

    @Test
    fun getProgram() {
        var url = "https://api.sr.se/api/v2/programs/index?channelid=132"

        `when`(mockRestTemplate.getForObject(url, ProgramsResponse::class.java)).thenReturn(programsResponse)

        val result = service.getProgram("p1", "test program")

        assertEquals("test program", result!!.name)
        verify(mockRestTemplate).getForObject(url, ProgramsResponse::class.java)

    }

    @Test
    fun getLatestEpisodes() {
        var url = "https://api.sr.se/api/v2/episodes/index?programid=1"

        `when`(mockRestTemplate.getForObject(url, EpisodesResponse::class.java)).thenReturn(episodesResponse)

        val result = service.getLatestEpisodes(1)

        assertEquals("test description", result.episodes[0].description)
        verify(mockRestTemplate).getForObject(url, EpisodesResponse::class.java)
    }


    @Test
    fun getLatestEpisodes_external_call_failed() {
        val exception = assertThrows<Exception> { service.getLatestEpisodes(1)}

        assertEquals("External API fetch from SR failed", exception.message)
    }
}