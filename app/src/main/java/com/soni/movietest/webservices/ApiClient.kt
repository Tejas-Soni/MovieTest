package com.soni.movietest.webservices

import android.app.Application
import com.soni.movietest.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object ApiClient {

    private val readTimeOut: Long = 50
    private val connectionTimeOut: Long = 50

    val BASE_URL = BuildConfig.BASE_URL
    val MOVIE_URL = BuildConfig.IMAGE_URL


    fun getClient(context: Application): ApiService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder().run {
            addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val token: String? = BuildConfig.API_TOKEN
                    val requestBuilder = chain.request().newBuilder()
                    token?.let {
                        requestBuilder.addHeader("Authorization", "Bearer $it")
                    }
                    requestBuilder.addHeader("Content-Type", "application/json")
                    return chain.proceed(requestBuilder.build())
                }
            }).addInterceptor(interceptor)
            connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
            readTimeout(readTimeOut, TimeUnit.SECONDS)
            build()
        }

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }


}