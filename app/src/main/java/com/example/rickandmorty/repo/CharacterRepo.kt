package com.example.rickandmorty.repo

import com.example.rickandmorty.api.CharactersAPI
import com.example.rickandmorty.model.CharacterDetails
import com.example.rickandmorty.util.Resource
import javax.inject.Inject

class CharacterRepo @Inject constructor(
    private val charactersAPI: CharactersAPI
) : CharacterRepoInterface {
    override suspend fun characterMain(): Resource<CharacterDetails> {
        return try {
            val response = charactersAPI.getCharacters()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data!", null)
        }
    }
}