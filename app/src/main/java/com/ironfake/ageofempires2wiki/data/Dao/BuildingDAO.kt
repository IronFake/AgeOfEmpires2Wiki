package com.ironfake.ageofempires2wiki.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Building
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface BuildingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllBuildings(unitList: List<Building>)

    @Insert
    fun addBuilding(building: Building)

    @Query("DELETE FROM buildings")
    fun deleteAll()

    @Query("select * from buildings")
    fun getAllBuildings(): Flowable<List<Building>>

    @Query("select * from buildings where name ==:buildingName ")
    fun getBuilding(buildingName: String): Single<Building>

    @Query("select * from buildings where name ==:buildingName ")
    fun getBuildings(buildingName: String): Single<List<Building>>

    @Query("select distinct name from buildings")
    fun getUniqueBuildingName(): Single<List<String>>

    @Query("select count(*) from buildings")
    fun getCount(): Single<Int>
}