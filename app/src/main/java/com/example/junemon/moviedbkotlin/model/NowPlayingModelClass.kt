package com.example.junemon.moviedbkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class NowPlayingModelClass(var results: List<Result>, var page: Int?,
                                var total_results: Int?, var dates: Dates, var total_pages: Int?) {
    @Parcelize
    class Result(var vote_count: Int?,
                 var id: Int?,
                 var video: Boolean?,
                 var vote_average: Double?,
                 var title: String?,
                 var popularity: Double?,
                 var poster_path: String?,
                 var original_language: String?,
                 var original_title: String?,
                 var genre_ids: List<Int>?,
                 var backdrop_path: String?,
                 var adult: Boolean?,
                 var overview: String?,
                 var release_date: String?) : Parcelable

    @Parcelize
    class Dates(var maximum: String?, var minimum: String?) : Parcelable
}