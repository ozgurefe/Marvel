package com.example.marvel.data.model

data class BaseModel<T>(
    val attributionHTML: String?,
    val attributionText: String?,
    val code: String?,
    val copyright: String?,
    val data: T?,
    val etag: String?,
    val status: String?
)