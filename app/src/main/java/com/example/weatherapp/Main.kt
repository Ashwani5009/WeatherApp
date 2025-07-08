package com.example.weatherapp

data class Main(
    val temp : Double,
    val feelsLike : String,
    val tempMin : Double,
    val tempMax : Double,
    val pressure : Int,
    val humidity : Int,
    val seaLevel : Int,
    val grndLevel : Int
)
