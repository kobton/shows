package com.radio.shows.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        val messageConverters = restTemplate.messageConverters
        messageConverters.add(MappingJackson2HttpMessageConverter()) // For JSON
        return restTemplate
    }
}
