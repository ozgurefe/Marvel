package com.example.marvel.ui.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.BaseFragment
import com.example.marvel.R
import com.example.marvel.data.model.Character
import com.example.marvel.databinding.FragmentCharacterDetailBinding
import com.example.marvel.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding>() {

    var character:Character? = null
    val comicsListAdapter = ComicsListAdapter()
    val splitRegex = "(*)"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = super.onCreateView(inflater, container, savedInstanceState)?.rootView
        prepareComicsRecyclerView()

        arguments?.getSerializable(Constants.CHARACTER)?.let {
            character = it as Character
        }

        character?.comics?.items?.forEach {
            it.name?.split("(")?.get(1)?.split(")")?.get(0)?.let { year->
                it.year = try{
                    Integer.valueOf(year)
                }
                catch (e:Exception){
                    0
                }
            }
        }

        val comisList = character?.comics?.items?.filter { it.year > 2005 && it.year != 0 }?.sortedBy { it.year }?.reversed()?.take(10)
        comicsListAdapter.submitList(comisList)

        return mView
    }

    private fun prepareComicsRecyclerView(){
        dataBinding.recyclerViewComics.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.recyclerViewComics.adapter = comicsListAdapter
    }

    override fun getLayoutRes(): Int = R.layout.fragment_character_detail
}