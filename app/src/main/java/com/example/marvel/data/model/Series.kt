package com.example.marvel.data.model

data class Series(
    val available: String?,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: String?
)