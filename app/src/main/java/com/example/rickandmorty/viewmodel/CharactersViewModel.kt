package com.example.rickandmorty.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.model.CharacterDetails
import com.example.rickandmorty.repo.CharacterRepoInterface
import com.example.rickandmorty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepoInterface,
) : ViewModel() {

    private val characters = MutableLiveData<Resource<CharacterDetails>>()
    val charList: LiveData<Resource<CharacterDetails>>
        get() = characters

    fun makeCharactersResponse() {
        viewModelScope.launch {
            val response = repository.characterMain()
            characters.value = response
        }
    }

}