package com.example.rickandmorty.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.rickandmorty.adapter.CharacterDetailsAdapter
import com.example.rickandmorty.adapter.CharactersAdapter
import javax.inject.Inject

class CharactersFragmentFactory @Inject constructor(
    private val charactersAdapter: CharactersAdapter,
    private val characterDetailsAdapter: CharacterDetailsAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){

            CharactersFragment::class.java.name -> CharactersFragment(charactersAdapter)
            CharacterDetailsFragment::class.java.name -> CharacterDetailsFragment(characterDetailsAdapter)

            else -> super.instantiate(classLoader, className)
        }
    }

}