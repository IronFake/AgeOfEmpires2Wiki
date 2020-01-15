package com.ironfake.ageofempires2wiki.model.aoeApiModels

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "units")
@TypeConverters(com.ironfake.ageofempires2wiki.api.TypeConverters::class)
data class Unit(
    @PrimaryKey
    val id : Int,
    val name: String,
    var image: String?,
    val description: String?,
    val expansion: String?,
    val age: String?,
    @SerializedName("created_in") var createdIn: String?,
    @Embedded @SerializedName("cost")val cost: Cost?,
    @SerializedName("build_time")  val buildTime : String?,
    @SerializedName("reload_time")  val reloadTime : String?,
    @SerializedName("attack_delay")  val attackDelay : String?,
    @SerializedName("movement_rate")  val movementRate : String?,
    @SerializedName("line_of_sight")  val lineOfSight : String?,
    @SerializedName("hit_points")   val hitPoints : String?,
    @SerializedName("range") val range: String?,
    val attack : String?,
    val armor: String?,
    val accuracy: String?,
    @SerializedName("attack_bonus") val attackBonus: List<String>?,
    @SerializedName("search_radius")  val searchRadius : String?,
    @SerializedName("blast_radius")  val blastRadius : String?,
    @SerializedName("armor_bonus")  val armorBonus: List<String>?
)
