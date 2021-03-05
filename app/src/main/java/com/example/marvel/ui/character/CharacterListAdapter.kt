package com.example.marvel.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.databinding.ItemCharacterBinding
import com.example.marvel.data.model.Character

class CharacterListAdapter : ListAdapter<Character, CharacterListAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var characterListener: CharacterListener

    fun setCharacterListener(characterListener: CharacterListener){
        this.characterListener = characterListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder  = ViewHolder.create(
        LayoutInflater.from(parent.context),parent,characterListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(val binding: ItemCharacterBinding,characterListener: CharacterListener) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                characterListener.clickPhoto(binding.character)
            }
        }

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup, characterListener: CharacterListener): ViewHolder {
                val itemBinding = ItemCharacterBinding.inflate(inflater, parent, false)
                return ViewHolder(
                    itemBinding,
                    characterListener
                )
            }
        }

        fun bind(character: Character?) {
            binding.character = character
            binding.executePendingBindings()
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean = oldItem == newItem
        }
    }

    interface CharacterListener{
        fun clickPhoto(character: Character?)
    }
}