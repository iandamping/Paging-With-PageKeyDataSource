package com.example.junemon.moviedbkotlin.ui.feature.main

import android.arch.paging.PagedList
import com.example.junemon.moviedbkotlin.base.BaseView
import com.example.junemon.moviedbkotlin.model.NowPlayingModelClass
import com.example.junemon.moviedbkotlin.model.PopularModelClass

interface MainActivityView : BaseView {
    fun getPopularMovieData(allData: PagedList<PopularModelClass.Result>?)
    fun getBanner(data: PagedList<NowPlayingModelClass.Result>?)
    fun getAllNowPlaying(data: NowPlayingModelClass)
}