package com.example.junemon.moviedbkotlin.ui.feature.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.FragmentActivity
import com.example.junemon.moviedbkotlin.base.BasePresenter
import com.example.junemon.moviedbkotlin.helper.NowPlayingViewModel
import com.example.junemon.moviedbkotlin.helper.PopularMovieViewModel
import io.reactivex.disposables.CompositeDisposable

class MainPresenter(var mView: MainActivityView, var target: FragmentActivity) : BasePresenter {
    private var bannerVm: NowPlayingViewModel? = null
    private var popularVm: PopularMovieViewModel? = null
    private lateinit var ctx: Context
    private lateinit var compose: CompositeDisposable


    override fun onCreate(context: Context) {
        this.ctx = context
        mView.initView()
        mView.initListener()
        compose = CompositeDisposable()
        bannerVm = ViewModelProviders.of(target).get(NowPlayingViewModel::class.java)
        popularVm = ViewModelProviders.of(target).get(PopularMovieViewModel::class.java)
    }

//    fun getNowPlaying(apikey: String) {
//        compose.add(MainApplication.getNewsData.getNowPlayingMovieData(apikey).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe { it -> mView.getAllNowPlaying(it) })
//    }

    fun getBanner() {
        bannerVm?.getAllMovies()?.observe(target, Observer {
            mView.getBanner(it)
        })
    }

    fun getPopularMovieData() {
        popularVm?.getAllMovies()?.observe(target, Observer {
            mView.getPopularMovieData(it)
        })
//        compose.add(MainApplication.getNewsData.getPopularMovieData(apikey).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe { it -> mView.getPopularMovieData(it.results) })
    }


    override fun onPause() {
    }

    override fun getContext(): Context? {
        return ctx
    }

    override fun onStop() {
        if (compose != null && compose.isDisposed) {
            compose.clear()
            compose.dispose()
        }
    }


}