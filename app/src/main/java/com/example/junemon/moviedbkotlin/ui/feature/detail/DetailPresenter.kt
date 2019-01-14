package com.example.junemon.moviedbkotlin.ui.feature.detail

import android.content.Context
import com.example.junemon.moviedbkotlin.MainApplication
import com.example.junemon.moviedbkotlin.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter(var mView: DetailView) : BasePresenter {
    lateinit var ctx: Context
    lateinit var compose: CompositeDisposable


    fun getDataDetail(movieID: Int?, apiKey: String) {
        compose.add(MainApplication.getNewsData.getDetailMovieData(movieID, apiKey).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { mView.getDetailMovie(it) })
    }

    override fun getContext(): Context? {
        return ctx
    }

    override fun onCreate(context: Context) {
        this.ctx = context
        mView.initView()
        mView.initListener()
        compose = CompositeDisposable()
    }

    override fun onPause() {
    }

    override fun onStop() {
        if (compose != null && compose.isDisposed) {
            compose.dispose()
        }
    }

//    fun onDestroy() {
//        if (compose != null && compose.isDisposed) {
//            compose.dispose()
//        }
//    }
}