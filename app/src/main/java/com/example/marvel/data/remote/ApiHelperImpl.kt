package com.example.marvel.data.remote

import com.example.marvel.data.model.BaseModel
import com.example.marvel.data.model.CharacterList
import com.example.marvel.util.Constants
import com.example.marvel.util.Resource
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService:ApiService):BaseDataSource(),ApiHelper {
    override suspend fun getCharacters(page:Int): Resource<BaseModel<CharacterList>> = getResult { apiService.getCharacters(md5("${timeStamp()}${Constants.API_PRIVATE_KEY}${Constants.API_PUBLIC_KEY}"),timeStamp().toString(),page*30) }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    private fun timeStamp(){
        Calendar.getInstance().timeInMillis
    }
}