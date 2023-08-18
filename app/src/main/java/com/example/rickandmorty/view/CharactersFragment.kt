package com.example.rickandmorty.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.adapter.CharactersAdapter
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.util.Status
import com.example.rickandmorty.viewmodel.CharactersViewModel
import javax.inject.Inject

class CharactersFragment @Inject constructor(
    private val charactersAdapter: CharactersAdapter
) : Fragment(R.layout.fragment_characters) {

    private var fragmentBinding: FragmentCharactersBinding? = null
    lateinit var viewModel: CharactersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[CharactersViewModel::class.java]
        val binding = FragmentCharactersBinding.bind(view)
        fragmentBinding = binding

        observeLiveData()

        binding.recyclerViewCharacters.adapter = charactersAdapter
        binding.recyclerViewCharacters.layoutManager = GridLayoutManager(requireContext(), 3)

    }

    override fun onResume() {
        super.onResume()
        viewModel.makeCharactersResponse()
    }

    private fun observeLiveData() {
        viewModel.charList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                    val charDataMain = it.data?.results?.toList()
                    charactersAdapter.characters = charDataMain!!

                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()

                }

                Status.LOADING -> {

                }
            }

        })
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}