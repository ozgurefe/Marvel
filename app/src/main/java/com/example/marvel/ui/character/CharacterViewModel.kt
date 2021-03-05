package com.example.marvel.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.data.model.BaseModel
import com.example.marvel.data.model.CharacterList
import com.example.marvel.data.repository.MainRepository
import com.example.marvel.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _liveDataCharacterList = MutableLiveData<Resource<BaseModel<CharacterList>>>()
    val liveDataCharacterList : LiveData<Resource<BaseModel<CharacterList>>> get() = _liveDataCharacterList

    fun getCharacterList(page:Int){
        viewModelScope.launch {
            _liveDataCharacterList.value =  mainRepository.getCharacters(page)
        }
    }
}