package com.miho.springrestclientexamples.services

import com.miho.api.domain.User

interface ApiService {

    fun getUsers(limit: Int): List<User>
}