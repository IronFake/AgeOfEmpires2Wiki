package com.ironfake.ageofempires2wiki.api

import com.ironfake.ageofempires2wiki.model.aoeApiModels.BuildingList
import com.ironfake.ageofempires2wiki.model.aoeApiModels.CivilizationList
import com.ironfake.ageofempires2wiki.model.aoeApiModels.TechnologyList
import com.ironfake.ageofempires2wiki.model.aoeApiModels.UnitList
import com.ironfake.ageofempires2wiki.model.newsApiModels.NewsArticle
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
                @Query("apiKey") apiKey: String): Single<NewsArticle>


    /**
     * Get the list of the news from the API
     */
    @GET("units")
    fun getAllUnits(): Single<UnitList>

    @GET("structures")
    fun getAllBuildings(): Single<BuildingList>

    @GET("technologies")
    fun getAllTech(): Single<TechnologyList>

    @GET("civilizations")
    fun getAllCivil(): Single<CivilizationList>

    companion object Factory {
        fun create(url: String): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}