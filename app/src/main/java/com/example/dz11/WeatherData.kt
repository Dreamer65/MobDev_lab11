package com.example.dz11

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData (
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val rain: Rain? = null,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long
)

@Serializable
data class Rain(
    @SerialName("1h")
    val t1h: Double
)

@Serializable
data class Clouds (
    val all: Long
)

@Serializable
data class Coord (
    val lon: Double,
    val lat: Double
)

@Serializable
data class Main (
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val humidity: Double
)

@Serializable
data class Sys (
    val type: Long = 0,
    val id: Long = 0,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

@Serializable
data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class Wind (
    val speed: Double,
    val deg: Double,
    val gust: Double = 0.0
)