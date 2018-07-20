package com.miho.api.domain

import com.fasterxml.jackson.annotation.JsonAlias

data class Card(var type: String, var number: String, @JsonAlias("expiration_date") var expirationDate: ExpirationDate,
                var iban: String, var swift: String)