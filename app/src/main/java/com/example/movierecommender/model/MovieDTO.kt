package com.example.movierecommender.model

class MovieDTO(
    var total: Int,
    var start: Int,
    var display: Int,
    var items: List<MovieItemDTO>
)

