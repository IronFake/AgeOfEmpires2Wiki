package com.ironfake.ageofempires2wiki.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Civilization
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CivilizationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllCivil(civilList: List<Civilization>)

    @Insert
    fun addCivil(civil: Civilization)

    @Query("DELETE FROM civilizations")
    fun deleteAll()

    @Query("select * from civilizations")
    fun getAllCivil(): Observable<List<Civilization>>

    @Query("select * from civilizations where id ==:position ")
    fun getCivil(position: Int): Single<Civilization>

    @Query("select count(*) from civilizations")
    fun getCount(): Single<Int>
}