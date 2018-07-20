package com.miho.springrestclientexamples.services

import com.miho.api.domain.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ApiService {

    fun getUsers(limit: Int): List<User>

    fun getUsers(limit: Mono<Int>): Flux<User>
}