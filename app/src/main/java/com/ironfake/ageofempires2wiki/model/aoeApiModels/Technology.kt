package com.ironfake.ageofempires2wiki.model.aoeApiModels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName


@Entity(tableName = "technologies")
@TypeConverters(com.ironfake.ageofempires2wiki.api.TypeConverters::class)
data class Technology(
    @PrimaryKey
    val id : Int,
    val name: String,
    var image: String?,
    val description: String?,
    val age: String?,
    @SerializedName("develops_in") var developsIn: String?,
    @Embedded val cost: Cost?,
    @SerializedName("build_time") val buildTime : String,
    @SerializedName("applies_to") val appliesTo: List<String>?)