package com.example.marvel.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.BaseFragment
import com.example.marvel.R
import com.example.marvel.databinding.FragmentCharacterBinding
import com.example.marvel.util.Constants
import com.example.marvel.util.PaginationScrollListener
import com.example.marvel.data.model.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding>(),CharacterListAdapter.CharacterListener {

    private val characterListAdapter = CharacterListAdapter()
    private val viewModel:CharacterViewModel by viewModels()
    private val arrayListCharacter = ArrayList<Character>()
    private var page = 0
    private var isLastPage = false
    private var isLoading = false
    lateinit var linearLayoutManager : LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mView = super.onCreateView(inflater, container, savedInstanceState)?.rootView

        init()
        prepareCharacterRecyclerView()
        listeners()
        getCharacterList()

        viewModel.liveDataCharacterList.observe(viewLifecycleOwner, {
            isLoading = false
            it.data?.data?.characters?.let { it1 ->
                if (it1.isEmpty()){
                    isLastPage = true
                }
                setCharacterList(it1)
            }
        })

        return mView
    }

    private fun init(){
        linearLayoutManager= LinearLayoutManager(requireContext())
    }

    private fun getCharacterList(){
        viewModel.getCharacterList(page)
        isLoading = true
    }

    private fun setCharacterList(listCharacter:List<Character>){
        arrayListCharacter.addAll(listCharacter)
        characterListAdapter.notifyDataSetChanged()
    }

    private fun listeners(){
        //Pagination'ı yapan kod
        dataBinding.recyclerViewCharacter.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager){
            override fun loadMoreItems() {
                page++
                getCharacterList()
            }
            override fun isLastPage(): Boolean = isLastPage
            override fun isLoading(): Boolean  = isLoading
        })
    }

    private fun prepareCharacterRecyclerView(){
        characterListAdapter.setCharacterListener(this)
        dataBinding.recyclerViewCharacter.layoutManager = linearLayoutManager
        dataBinding.recyclerViewCharacter.adapter = characterListAdapter
        characterListAdapter.submitList(arrayListCharacter)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_character

    override fun clickPhoto(character: Character?) {
        val bundle = bundleOf(Constants.CHARACTER to character)
        findNavController().navigate(R.id.action_fragment_character_to_fragment_character_detail,bundle)}
}