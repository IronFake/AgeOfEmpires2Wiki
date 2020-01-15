package com.ironfake.ageofempires2wiki.model.newsApiModels

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class NewsArticle {
    @SerializedName("articles")
    @Expose
    var news: List<News>? = null

}