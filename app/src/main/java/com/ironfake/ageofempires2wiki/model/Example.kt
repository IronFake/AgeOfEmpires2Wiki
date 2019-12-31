package com.ironfake.ageofempires2wiki.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Example {
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults = 0
    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null

}