package com.miho.springrestclientexamples.services

import com.miho.api.domain.User
import com.miho.api.domain.UserData
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ApiServiceImpl(private val restTemplate: RestTemplate) : ApiService {

    override fun getUsers(limit: Int): List<User> =
            restTemplate.getForObject("http://apifaketory.com/api/user?limit=$limit", UserData::class.java)?.data
                    ?: throw RuntimeException("it all went to shit")
}