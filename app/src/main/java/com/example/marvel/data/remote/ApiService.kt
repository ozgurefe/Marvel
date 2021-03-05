package com.example.marvel.data.remote


import com.example.marvel.data.model.BaseModel
import com.example.marvel.data.model.CharacterList
import com.example.marvel.util.Constants
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/v1/public/characters?apikey=${Constants.API_PUBLIC_KEY}&limit=30")
    suspend fun getCharacters(@Query("hash") hash:String,@Query("ts") timeStamp:String,@Query("offset") page:Int):Response<BaseModel<CharacterList>>

}