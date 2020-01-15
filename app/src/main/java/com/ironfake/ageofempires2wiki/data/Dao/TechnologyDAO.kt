package com.ironfake.ageofempires2wiki.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Technology
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface TechnologyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllTechs(unitList: List<Technology>)

    @Insert
    fun addTech(tech: Technology)

    @Query("DELETE FROM technologies")
    fun deleteAll()

    @Query("select * from technologies")
    fun getAllTechs(): Flowable<List<Technology>>

    @Query("select * from technologies where name ==:techName ")
    fun getTech(techName: String): Single<Technology>

    @Query("select * from technologies where developsIn ==:developIn ")
    fun getTechnologies(developIn: String): Single<List<Technology>>

    @Query("select count(*) from technologies")
    fun getCount(): Single<Int>
}