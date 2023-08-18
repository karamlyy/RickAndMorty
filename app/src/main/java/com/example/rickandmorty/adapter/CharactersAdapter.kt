package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import javax.inject.Inject
import com.example.rickandmorty.R
import com.example.rickandmorty.model.Result
import com.example.rickandmorty.view.CharactersFragmentDirections


class CharactersAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }


    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var characters: List<Result>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.characters_row, parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {

        val imageView = holder.itemView.findViewById<ImageView>(R.id.charactersImage)
        val charNameText = holder.itemView.findViewById<TextView>(R.id.charactersName)
        val chars = characters[position]
        holder.itemView.apply {
            charNameText.text = chars.name
            glide.load(chars.image).into(imageView)
        }

        holder.itemView.setOnClickListener {
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(
                    charID = chars.id,
                    charImage = chars.image,
                    charLocation = chars.location.name,
                    charName = chars.name,
                    charStatus = chars.status
                )
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return characters.size
    }
}