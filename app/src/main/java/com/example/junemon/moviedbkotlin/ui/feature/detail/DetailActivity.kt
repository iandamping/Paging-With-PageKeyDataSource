package com.example.junemon.moviedbkotlin.ui.feature.detail

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.junemon.moviedbkotlin.R
import com.example.junemon.moviedbkotlin.model.DetailMovieClass
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailActivity : AppCompatActivity(), DetailView {
    lateinit var presenter: DetailPresenter
    var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        presenter = DetailPresenter(this)
        presenter.onCreate(this)
        presenter.getDataDetail(movieId, getString(R.string.api_key))
    }

    override fun getDetailMovie(data: DetailMovieClass) {
        tvDetailTittles.text = data.original_title
        tvDetailTaglines.text = data.tagline
        tvDetailReleaseDate.text = data.release_date
        tvDetailOverview.text = data.overview
        Observable.fromIterable(data.genres).subscribe { tvDetailGenres.append(it.name + ", ") }
        Observable.fromIterable(data.production_companies).subscribe { tvDetailProductionCompanies.append(it.name + ", ") }
        appbarDetailLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, i ->
            var isShow: Boolean = true
            var scrollRange: Int = -1

            if (scrollRange == -1) {
                scrollRange = appBarLayout.getTotalScrollRange();
            }
            if (scrollRange + i == 0) {
                collapsingToolbar.title = data.original_title
                tvDetailTittles.visibility = View.GONE
                isShow = true;
            } else if (isShow) {
                collapsingToolbar.title = " "
                tvDetailTittles.visibility = View.VISIBLE
                isShow = false;
            }
        })


        tvDetailBudget.text = data.budget.toString()
        tvDetailRevenue.text = data.revenue
        tvDetailMovieVote.text = data.vote_average.toString()
        tvDetailMovieRuntime.text = data.runtime.toString() + " M"
        Picasso.get().load(getString(R.string.image_formater) + data.poster_path).into(ivDetailMovieImages)
//        Picasso.get().load(getString(R.string.image_formater) + data.backdrop_path).into(ivDetailBackdrop)
    }

    override fun initView() {
        val i: Intent = intent
        movieId = i.getIntExtra("key", 338952)
    }

    override fun initListener() {
    }

}