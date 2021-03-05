package com.example.marvel.ui.character_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.databinding.ItemCharacterBinding
import com.example.marvel.data.model.Character
import com.example.marvel.data.model.Item
import com.example.marvel.databinding.ItemComicBinding

class ComicsListAdapter : ListAdapter<Item, ComicsListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ViewHolder  = ViewHolder.create(
        LayoutInflater.from(parent.context),parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root){

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
                val itemBinding = ItemComicBinding.inflate(inflater, parent, false)
                return ViewHolder(
                    itemBinding
                )
            }
        }

        fun bind(comics: Item?) {
            binding.comics = comics
            binding.executePendingBindings()
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(
                oldItem: Item,
                newItem: Item
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: Item,
                newItem: Item
            ): Boolean = oldItem == newItem
        }
    }
}