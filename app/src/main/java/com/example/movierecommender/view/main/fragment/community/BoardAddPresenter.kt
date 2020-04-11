package com.example.movierecommender.view.main.fragment.community

import android.util.Log
import com.example.movierecommender.network.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.collections.HashMap

class BoardAddPresenter : BoardAddContract.Presenter {
    lateinit var view: BoardAddContract.View

    override fun addBoard(boardData: HashMap<String, String?>) {
        Service.create().boardRegister(boardData)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.get("success").asString == "true") {
                    view.showToast("글 생성")
                    view.moveFragment("community")
                } else
                    view.showToast("글 생성 실패")
            }, { Log.d("글 생성 오류", it.localizedMessage) })
    }
}