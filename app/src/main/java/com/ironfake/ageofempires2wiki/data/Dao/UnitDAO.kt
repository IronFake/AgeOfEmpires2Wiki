package com.ironfake.ageofempires2wiki.data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UnitDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllUnits(unitList: List<Unit>)

    @Insert
    fun addUnit(unit: Unit)

    @Query("DELETE FROM units")
    fun deleteAll()

    @Query("select * from units")
    fun getAllUnits(): Flowable<List<Unit>>

    @Query("select * from units where name ==:unitName ")
    fun getUnit(unitName: String): Single<Unit>

    @Query("select * from units where id ==:id ")
    fun getUnit(id: Int): Single<Unit>

    @Query("select * from units where createdIn ==:createdIn ")
    fun getUnitsByBuilding(createdIn: String): Single<List<Unit>>

    @Query("select * from units where age='Dark'")
    fun getAllDarkUnits(): Flowable<List<Unit>>

    @Query("select * from units where age='Feudal'")
    fun getAllFeudalUnits(): Flowable<List<Unit>>

    @Query("select * from units where age='Castle'")
    fun getAllCastleUnits(): Flowable<List<Unit>>

    @Query("select * from units where age='Imperial'")
    fun getAllImperialUnits(): Single<List<Unit>>

    @Query("select count(*) from units")
    fun getCount(): Single<Int>
}
