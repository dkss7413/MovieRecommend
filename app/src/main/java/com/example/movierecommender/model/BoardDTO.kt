package com.example.movierecommender.model

data class BoardDTO(
    var boardId: String,
    var userId: String,
    var nickname: String,
    var boardTitle: String,
    var boardDate: String,
    var boardContent: String
)