package com.example.junemon.moviedbkotlin.helper

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import com.example.junemon.moviedbkotlin.MainApplication
import com.example.junemon.moviedbkotlin.model.PopularModelClass
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PopularMoviePageKeyedDataSource() : PageKeyedDataSource<Int, PopularModelClass.Result>() {
    private val page = 1
    private var composite: CompositeDisposable = CompositeDisposable()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PopularModelClass.Result>) {
        composite.add(MainApplication.getNewsData.getPopularMovieData("9986464dc7ff8e83569e65a98dc7b3b6", page)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.results, null, page + 1)
                }, {
                    Log.e("Load Initial", it.message)
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PopularModelClass.Result>) {
        composite.add(MainApplication.getNewsData.getPopularMovieData("9986464dc7ff8e83569e65a98dc7b3b6", params.key)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.results, params.key + 1)
                }, {
                    Log.e("Load After", it.message)
                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PopularModelClass.Result>) {
        val adjacentKey = if (params.key > 1) params.key - 1 else null
        composite.add(MainApplication.getNewsData.getPopularMovieData("9986464dc7ff8e83569e65a98dc7b3b6", params.key)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.results, adjacentKey)
                }, {
                    Log.e("Load After", it.message)
                }))
    }
}