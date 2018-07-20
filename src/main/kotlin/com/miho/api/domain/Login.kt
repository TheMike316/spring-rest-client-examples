package com.miho.api.domain

data class Login(var username: String, var password: String, var md5: String,
                 var sha1: String, var sha256: String)