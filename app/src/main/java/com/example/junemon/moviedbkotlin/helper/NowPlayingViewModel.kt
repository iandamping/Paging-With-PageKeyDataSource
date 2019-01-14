package com.example.junemon.moviedbkotlin.helper

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.junemon.moviedbkotlin.model.NowPlayingModelClass

class NowPlayingViewModel(application: Application) : AndroidViewModel(application) {
    private val pageSize = 20
    private val isPlaceHolder = true
    private lateinit var movieList: LiveData<PagedList<NowPlayingModelClass.Result>>

    fun getAllMovies(): LiveData<PagedList<NowPlayingModelClass.Result>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(isPlaceHolder)
                .setInitialLoadSizeHint(pageSize)
                .setPageSize(20)
                .build()
        movieList = initPagedListBuilder(config).build()
        return movieList
    }


    private fun initPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, NowPlayingModelClass.Result> {
        val dataSourceFactory = object : DataSource.Factory<Int, NowPlayingModelClass.Result>() {
            override fun create(): DataSource<Int, NowPlayingModelClass.Result> {
                return NowPlayingPageKeyDataSource()
            }
        }
        return LivePagedListBuilder<Int, NowPlayingModelClass.Result>(dataSourceFactory, config)
    }
}