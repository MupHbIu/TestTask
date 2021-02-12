package com.test.testtask.domain.entities

data class Weather(
        val name: String, // Town
        val main: Main, // Temperature + Humidity
        val clouds: Clouds // Clouds
)
