package com.example.movierecommender.view.main.fragment.community

import com.example.movierecommender.network.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CommunityPresenter: CommunityContract.Presenter {
    lateinit var view: CommunityContract.View

    override fun setList() {
        Service.create().boardGet()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.setCommunityListAdapter(it)
            }, { })
    }
}