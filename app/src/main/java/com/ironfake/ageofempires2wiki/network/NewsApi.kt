package com.ironfake.ageofempires2wiki.network

import com.ironfake.ageofempires2wiki.model.Example
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    /**
     * Get the list of the news from the API
     */
    @GET("/v2/everything")
    fun getNews(@Query("q") query : String,
                @Query("apiKey") apiKey: String): Single<Example>

}