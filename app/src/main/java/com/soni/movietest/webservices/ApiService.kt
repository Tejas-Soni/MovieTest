package com.soni.movietest.webservices

import com.soni.movietest.BuildConfig
import com.soni.movietest.webservices.responsebean.MovieDetailsResponse
import com.soni.movietest.webservices.responsebean.TopRatedMoviesResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @POST("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query(("api_key")) apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US"
    )
            : TopRatedMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query(("api_key")) apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US"
    ): MovieDetailsResponse
}
