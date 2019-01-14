package com.example.junemon.moviedbkotlin.ui.feature.main

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.moviedbkotlin.R
import com.example.junemon.moviedbkotlin.model.PopularModelClass
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie_db.*

class MainPopularPagingAdapter(var ctx: Context, var listener: (PopularModelClass.Result) -> Unit) : PagedListAdapter<PopularModelClass.Result, MainPopularPagingAdapter.ViewHolderPagingPopular>(diffCallbacks) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolderPagingPopular {
        return ViewHolderPagingPopular(LayoutInflater.from(ctx).inflate(R.layout.item_movie_db, p0, false), ctx)
    }

    override fun onBindViewHolder(p0: ViewHolderPagingPopular, p1: Int) {
        val data = getItem(p1)
        if (data != null) {
            p0.bindViews(data, listener)
        }
    }


    class ViewHolderPagingPopular(override var containerView: View, var ctx: Context?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindViews(data: PopularModelClass.Result, listener: (PopularModelClass.Result) -> Unit) {
            if (data.adult!!) {
                ivAdultMovie.visibility = View.VISIBLE
            }
            tvMovieTittles.text = data.title
            tvMovieDateRelease.text = data.release_date
            Picasso.get().load(ctx?.getString(R.string.image_formater) + data.poster_path).into(ivMovieImages)
            itemView.setOnClickListener { listener(data) }
        }
    }

    companion object {
        private val diffCallbacks = object : DiffUtil.ItemCallback<PopularModelClass.Result>() {
            override fun areItemsTheSame(oldItem: PopularModelClass.Result, newItem: PopularModelClass.Result): Boolean =
                    oldItem.poster_path == newItem.poster_path

            override fun areContentsTheSame(oldItem: PopularModelClass.Result, newItem: PopularModelClass.Result): Boolean =
                    oldItem == newItem
        }
    }
}