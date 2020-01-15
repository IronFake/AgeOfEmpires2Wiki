package com.ironfake.ageofempires2wiki.model.newsApiModels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class NewsSource {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}