package com.example.rickandmorty.repo

import com.example.rickandmorty.model.CharacterDetails
import com.example.rickandmorty.util.Resource

interface CharacterRepoInterface {

    suspend fun characterMain(): Resource<CharacterDetails>
}