package com.ironfake.ageofempires2wiki.model.aoeApiModels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "buildings")
@TypeConverters(com.ironfake.ageofempires2wiki.api.TypeConverters::class)
data class Building(
    @PrimaryKey
    val id : Int,
    val name: String,
    var image: String?,
    val expansion: String?,
    val age: String?,
    @Embedded val cost: Cost?,
    @SerializedName("build_time") val buildTime : String?,
    @SerializedName("hit_points")  val hitPoints : String?,
    @SerializedName("line_of_sight") val lineOfSight : String?,
    val armor: String?,
    val special: List<String>?,
    @SerializedName("reload_time") val reloadTime : String?,
    val range: String?,
    val attack : String?)
