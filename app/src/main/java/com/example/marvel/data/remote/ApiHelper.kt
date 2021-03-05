package com.example.marvel.data.remote

import com.example.marvel.data.model.BaseModel
import com.example.marvel.data.model.CharacterList
import com.example.marvel.util.Resource

interface ApiHelper {
    suspend fun getCharacters(page:Int): Resource<BaseModel<CharacterList>>
}