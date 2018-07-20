package com.miho.api.domain

data class User(var gender: String,
                var name: Name,
                var location: Location,
                var email: String,
                var login: Login,
                var phone: String,
                var job: Job,
                var billing: Billing,
                var language: String,
                var currency: String)