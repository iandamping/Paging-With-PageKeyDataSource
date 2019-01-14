package com.example.junemon.moviedbkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class TopRatedModelClass(var page: Int?, var totalResults: Int?, var totalPages: Int?, var results: List<Result>) {
    @Parcelize
    class Result(var voteCount: Int?,
                 var id: Int?,
                 var video: Boolean?,
                 var voteAverage: Double?,
                 var title: String?,
                 var popularity: Double?,
                 var posterPath: String?,
                 var originalLanguage: String?,
                 var originalTitle: String?,
                 var genreIds: List<Int>?,
                 var backdropPath: String?,
                 var adult: Boolean?,
                 var overview: String?,
                 var releaseDate: String?) : Parcelable
}