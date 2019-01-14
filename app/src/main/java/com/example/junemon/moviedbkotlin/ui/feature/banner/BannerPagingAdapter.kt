package com.example.junemon.moviedbkotlin.ui.feature.banner

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.junemon.moviedbkotlin.R
import com.example.junemon.moviedbkotlin.model.NowPlayingModelClass
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_slider.*

class BannerPagingAdapter(var ctx: Context?, var listener: (NowPlayingModelClass.Result) -> Unit) : PagedListAdapter<NowPlayingModelClass.Result, BannerPagingAdapter.BannerPagingViewHolders>(diffCallbacks) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BannerPagingViewHolders {
        return BannerPagingViewHolders(LayoutInflater.from(ctx).inflate(R.layout.item_slider, p0, false), ctx)
    }

    override fun onBindViewHolder(p0: BannerPagingViewHolders, p1: Int) {
        val data = getItem(p1)
        if (data != null) {
            p0.bindViews(data, listener)
        }
    }

    class BannerPagingViewHolders(override var containerView: View, var ctx: Context?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindViews(data: NowPlayingModelClass.Result, listener: (NowPlayingModelClass.Result) -> Unit) {
            tvMainTittleSliderBanner.text = data.original_title
            Picasso.get().load(ctx?.getString(R.string.image_formater) + data.backdrop_path).into(ivMainSliderBanner)
            itemView.setOnClickListener { listener(data) }
        }
    }

    companion object {
        private val diffCallbacks = object : DiffUtil.ItemCallback<NowPlayingModelClass.Result>() {
            override fun areItemsTheSame(oldItem: NowPlayingModelClass.Result, newItem: NowPlayingModelClass.Result): Boolean =
                    oldItem.poster_path == newItem.poster_path

            override fun areContentsTheSame(oldItem: NowPlayingModelClass.Result, newItem: NowPlayingModelClass.Result): Boolean =
                    oldItem == newItem
        }
    }
}