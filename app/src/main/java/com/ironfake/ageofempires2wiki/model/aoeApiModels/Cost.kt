package com.ironfake.ageofempires2wiki.model.aoeApiModels

import com.google.gson.annotations.SerializedName

data class Cost(
    @SerializedName("Wood")val wood : String?,
    @SerializedName("Gold")val gold : String?,
    @SerializedName("Food")val food : String?,
    @SerializedName("Stone")val stone : String?)