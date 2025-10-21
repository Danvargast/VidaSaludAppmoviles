package com.example.vidasalud.data.model


data class Post(
    val id: Int,
    val autor: String,
    val contenido: String,
    val fecha: String,
    var likes: Int = 0
)
