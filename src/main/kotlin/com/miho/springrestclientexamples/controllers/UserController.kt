package com.miho.springrestclientexamples.controllers

import com.miho.springrestclientexamples.services.ApiService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.server.adapter.DefaultServerWebExchange

@Controller
class UserController(private val apiService: ApiService) {

    @GetMapping("", "/", "/index")
    fun getIndexPage() = "index"

    @PostMapping("/users")
    fun formPost(model: Model, serverWebExchange: DefaultServerWebExchange): String {
        val limit = serverWebExchange.formData
                .map { it.getFirst("limit")?.toInt() ?: throw RuntimeException("Something went wrong. Get your life together, Carol!") }

        model.addAttribute("users", apiService.getUsers(limit))

        return "userlist"
    }
}