package com.example.rickandmorty.model

data class Result(
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val status: String,
)