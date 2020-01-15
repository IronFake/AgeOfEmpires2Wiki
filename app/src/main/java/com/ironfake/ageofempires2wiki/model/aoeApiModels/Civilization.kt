package com.ironfake.ageofempires2wiki.model.aoeApiModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "civilizations")
@TypeConverters(com.ironfake.ageofempires2wiki.api.TypeConverters::class)
data class Civilization(
    @PrimaryKey
    val id : Int,
    val name: String,
    var image: String?,
    val expansion: String?,
    @SerializedName("army_type")val armyType: String?,
    @SerializedName("unique_unit") val uniqueUnit: List<String>?,
    @SerializedName("unique_tech") var uniqueTech: List<String>?,
    @SerializedName("team_bonus") val teamBonus: String?,
    @SerializedName("civilization_bonus") val civilizationBonus: List<String>?)
