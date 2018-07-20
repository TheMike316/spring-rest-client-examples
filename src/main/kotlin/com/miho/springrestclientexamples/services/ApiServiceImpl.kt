package com.miho.springrestclientexamples.services

import com.miho.api.domain.User
import com.miho.api.domain.UserData
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class ApiServiceImpl(private val restTemplate: RestTemplate, @Value("\${api.url}") private val apiUrl: String) : ApiService {

    //for whatever reason, the api always returns one additional data record
    override fun getUsers(limit: Int): List<User> {
        val uriComponentsBuilder = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("limit", limit)

        return restTemplate.getForObject(uriComponentsBuilder.toUriString(), UserData::class.java)?.data
                ?: throw RuntimeException("it all went to shit")
    }
}