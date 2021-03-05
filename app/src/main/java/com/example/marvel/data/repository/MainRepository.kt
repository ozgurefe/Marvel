package com.example.marvel.data.repository

import com.example.marvel.data.remote.ApiHelper
import com.example.marvel.data.model.BaseModel
import com.example.marvel.data.model.CharacterList
import com.example.marvel.util.Resource
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getCharacters(page:Int): Resource<BaseModel<CharacterList>> = apiHelper.getCharacters(page)
}