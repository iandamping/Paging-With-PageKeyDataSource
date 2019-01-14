package com.example.junemon.moviedbkotlin.repository

import com.example.junemon.moviedbkotlin.MainApplication
import com.example.junemon.moviedbkotlin.model.TopRatedModelClass
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieRepository {
    var compose: CompositeDisposable
    var data: TopRatedModelClass? = null

    init {
        compose = CompositeDisposable()
    }

    fun getMovies(apiKey: String): TopRatedModelClass? {
        compose.add(MainApplication.getNewsData.getTopRatedMovieData(apiKey).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { it -> data })
        return data
    }
}