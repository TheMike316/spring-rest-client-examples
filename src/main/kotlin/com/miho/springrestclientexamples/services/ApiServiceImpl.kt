package com.miho.springrestclientexamples.services

import com.miho.api.domain.User
import com.miho.api.domain.UserData
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@Service
//for whatever reason, the api always returns one additional data record
class ApiServiceImpl(private val restTemplate: RestTemplate, @Value("\${api.url}") private val apiUrl: String) : ApiService {

    override fun getUsers(limit: Int): List<User> {
        val uriComponentsBuilder = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("limit", limit)

        return restTemplate.getForObject(uriComponentsBuilder.toUriString(), UserData::class.java)?.data
                ?: throw RuntimeException("it all went to shit")
    }

    override fun getUsers(limit: Mono<Int>) = WebClient.create(apiUrl)
            .get()
            .uri { it.queryParam("limit", limit.block()).build() }
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .flatMap { it.bodyToMono(UserData::class.java) }
            .flatMapIterable(UserData::data)
}
