package com.example.rickandmorty.api

import com.example.rickandmorty.model.CharacterDetails
import retrofit2.Response
import retrofit2.http.GET

interface CharactersAPI {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterDetails>

}