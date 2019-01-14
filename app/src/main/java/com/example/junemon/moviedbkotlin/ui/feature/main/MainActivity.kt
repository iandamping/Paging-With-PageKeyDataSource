package com.example.junemon.moviedbkotlin.ui.feature.main

import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import com.example.junemon.moviedbkotlin.R
import com.example.junemon.moviedbkotlin.model.NowPlayingModelClass
import com.example.junemon.moviedbkotlin.model.PopularModelClass
import com.example.junemon.moviedbkotlin.ui.feature.banner.BannerPagingAdapter
import com.example.junemon.moviedbkotlin.ui.feature.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var popularAdapter: MainPopularPagingAdapter
    private lateinit var bannerAdapter: BannerPagingAdapter
    private lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, this)
        presenter.onCreate(this)
        presenter.getPopularMovieData()
        presenter.getBanner()
        bannerAdapter = BannerPagingAdapter(this) {
            startActivity(intentFor<DetailActivity>("key" to it.id).singleTop())
        }
        popularAdapter = MainPopularPagingAdapter(this) {
            startActivity(intentFor<DetailActivity>("key" to it.id).singleTop())

        }

    }


    override fun getPopularMovieData(allData: PagedList<PopularModelClass.Result>?) {
        rvMainCard.layoutManager = GridLayoutManager(this, 2)
        rvMainCard.adapter = popularAdapter
        popularAdapter.submitList(allData)
    }


    override fun getAllNowPlaying(data: NowPlayingModelClass) {
    }

    override fun getBanner(data: PagedList<NowPlayingModelClass.Result>?) {
        val snapHelper: SnapHelper = LinearSnapHelper()
        rvBannerSlider.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        snapHelper.attachToRecyclerView(rvBannerSlider)
        rvBannerSlider.adapter = bannerAdapter
        bannerAdapter.submitList(data)
    }


    override fun initView() {
    }

    override fun initListener() {
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

}
