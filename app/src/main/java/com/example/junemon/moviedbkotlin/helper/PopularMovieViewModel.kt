package com.example.junemon.moviedbkotlin.helper

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.junemon.moviedbkotlin.model.PopularModelClass

class PopularMovieViewModel(application: Application) : AndroidViewModel(application) {
    private val pageSize = 20
    private val isPlaceHolder = true
    private lateinit var movieList: LiveData<PagedList<PopularModelClass.Result>>

    fun getAllMovies(): LiveData<PagedList<PopularModelClass.Result>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(isPlaceHolder)
                .setInitialLoadSizeHint(pageSize)
                .setPageSize(20)
                .build()
        movieList = initPagedListBuilder(config).build()
        return movieList
    }


    private fun initPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, PopularModelClass.Result> {
        val dataSourceFactory = object : DataSource.Factory<Int, PopularModelClass.Result>() {
            override fun create(): DataSource<Int, PopularModelClass.Result> {
                return PopularMoviePageKeyedDataSource()
            }
        }
        return LivePagedListBuilder<Int, PopularModelClass.Result>(dataSourceFactory, config)
    }
}