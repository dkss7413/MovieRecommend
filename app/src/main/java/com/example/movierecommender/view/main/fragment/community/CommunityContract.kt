package com.example.movierecommender.view.main.fragment.community

import com.example.movierecommender.model.BoardDTO

interface CommunityContract{
    interface View{
        fun setCommunityListAdapter(boardList: ArrayList<BoardDTO?>?)
    }

    interface Presenter{
        fun setList()
    }
}