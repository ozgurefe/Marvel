package com.example.marvel.data.model

import com.google.gson.annotations.SerializedName

data class CharacterList(
    val count: String?,
    val limit: String?,
    val offset: String?,
    @SerializedName("results")
    val characters: List<Character>?,
    val total: String?
)