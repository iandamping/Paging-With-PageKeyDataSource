package com.example.junemon.moviedbkotlin.networking

import com.example.junemon.moviedbkotlin.model.DetailMovieClass
import com.example.junemon.moviedbkotlin.model.NowPlayingModelClass
import com.example.junemon.moviedbkotlin.model.PopularModelClass
import com.example.junemon.moviedbkotlin.model.TopRatedModelClass
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET(ApiConfig.getTopRatedMovie)
    fun getTopRatedMovieData(@Query("api_key") apiKey: String): Observable<TopRatedModelClass>

    @GET(ApiConfig.getPopularMovie)
    fun getPopularMovieData(@Query("api_key") apiKey: String, @Query("page") page: Int): Observable<PopularModelClass>

    @GET(ApiConfig.getDetailMovie + "{movie}")
    fun getDetailMovieData(@Path("movie") movieId: Int?, @Query("api_key") apiKey: String?): Observable<DetailMovieClass>

    @GET(ApiConfig.getNowPlayingMovie)
    fun getNowPlayingMovieData(@Query("api_key") apiKey: String, @Query("page") page: Int): Observable<NowPlayingModelClass>

}