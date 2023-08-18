package com.example.rickandmorty.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.adapter.CharacterDetailsAdapter
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.viewmodel.CharactersViewModel
import javax.inject.Inject

class CharacterDetailsFragment @Inject constructor(
    private val characterDetailsAdapter: CharacterDetailsAdapter,
) : Fragment(R.layout.fragment_character_details) {

    private var fragmentBinding: FragmentCharacterDetailsBinding? = null
    lateinit var viewModel: CharactersViewModel

    private var chardetailID = 0
    private var charDetailImage = ""
    private var charDetailStatus = ""
    private var charDetailName = ""
    private var charDetailLocation = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            chardetailID = CharacterDetailsFragmentArgs.fromBundle(it).charID
            charDetailImage = CharacterDetailsFragmentArgs.fromBundle(it).charImage
            charDetailStatus = CharacterDetailsFragmentArgs.fromBundle(it).charStatus
            charDetailName = CharacterDetailsFragmentArgs.fromBundle(it).charName
            charDetailLocation = CharacterDetailsFragmentArgs.fromBundle(it).charLocation
        }
        println(chardetailID)

        viewModel = ViewModelProvider(requireActivity())[CharactersViewModel::class.java]
        val binding = FragmentCharacterDetailsBinding.bind(view)
        fragmentBinding = binding

        binding.recyclerViewDetails.adapter = characterDetailsAdapter
        binding.recyclerViewDetails.layoutManager = LinearLayoutManager(requireContext())

        val charDetailList = ArrayList<String>()
        charDetailList.add(charDetailImage)
        charDetailList.add(charDetailStatus)
        charDetailList.add(charDetailLocation)
        charDetailList.add(charDetailName)

        characterDetailsAdapter.characterDetails = charDetailList
    }


    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }


}