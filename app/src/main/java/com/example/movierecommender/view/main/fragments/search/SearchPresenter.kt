package com.example.movierecommender.view.main.fragments.search

class SearchPresenter : SearchContract.Presenter{
    lateinit var view: SearchContract.View

    override fun setSearchList() {
        view.searchEnterButton()
        view.setSearchButton()
    }
}