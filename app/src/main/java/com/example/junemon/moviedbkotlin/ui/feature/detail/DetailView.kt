package com.example.junemon.moviedbkotlin.ui.feature.detail

import com.example.junemon.moviedbkotlin.base.BaseView
import com.example.junemon.moviedbkotlin.model.DetailMovieClass

interface DetailView : BaseView {
    fun getDetailMovie(data: DetailMovieClass)
}