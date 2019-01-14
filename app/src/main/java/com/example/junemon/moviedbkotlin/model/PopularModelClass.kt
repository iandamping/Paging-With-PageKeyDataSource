package com.example.junemon.moviedbkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class PopularModelClass(var page: Int?, var total_results: Int?, var total_pages: Int?, var results: List<Result>) {
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
}