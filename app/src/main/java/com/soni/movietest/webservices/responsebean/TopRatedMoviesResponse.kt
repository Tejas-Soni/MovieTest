package com.soni.movietest.webservices.responsebean
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
data class TopRatedMoviesResponse(
    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("total_results")
    val totalResults: Int? = 0,
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @SerializedName("results")
    val results: ArrayList<Result>? = arrayListOf()
)
@Entity
data class Result(
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Double? = 0.0,
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int? = 0,
    @ColumnInfo(name = "video")
    @SerializedName("video")
    val video: Boolean? = false,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @PrimaryKey
    @SerializedName("id")
    val id: Int? = 0,
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean? = false,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val originalTitle: String? = "",
//    @SerializedName("genre_ids")
//    val genreIds: List<Int>? = listOf(),
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = "",
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String? = "",
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String? = ""
)