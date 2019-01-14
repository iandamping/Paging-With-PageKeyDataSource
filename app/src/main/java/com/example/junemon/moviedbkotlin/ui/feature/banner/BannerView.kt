package com.example.junemon.moviedbkotlin.ui.feature.banner

import com.example.junemon.moviedbkotlin.base.BaseView
import com.example.junemon.moviedbkotlin.model.NowPlayingModelClass

interface BannerView : BaseView {
    fun getAllNowPlaying(data: NowPlayingModelClass)
}