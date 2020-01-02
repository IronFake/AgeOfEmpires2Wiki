package com.ironfake.ageofempires2wiki.api

import com.ironfake.ageofempires2wiki.model.Example
import com.ironfake.ageofempires2wiki.utils.BASE_URL
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    /**
     * Get the list of the news from the API
     */
    @GET("/v2/everything")
    fun getNews(@Query("q") query : String,
                @Query("apiKey") apiKey: String): Single<Example>


    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}