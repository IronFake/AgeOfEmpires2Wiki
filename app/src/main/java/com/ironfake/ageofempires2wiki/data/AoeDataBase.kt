package com.ironfake.ageofempires2wiki.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ironfake.ageofempires2wiki.data.Dao.BuildingDAO
import com.ironfake.ageofempires2wiki.data.Dao.CivilizationDAO
import com.ironfake.ageofempires2wiki.data.Dao.TechnologyDAO
import com.ironfake.ageofempires2wiki.data.Dao.UnitDAO
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Building
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Civilization
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Technology
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit

@Database(entities = [(Unit::class), (Building::class), (Technology::class),
    (Civilization::class)], version = 1)
abstract class AoeDataBase : RoomDatabase(){

    abstract fun getUnitDAO(): UnitDAO

    abstract fun getTechDAO(): TechnologyDAO

    abstract fun getBuildingDAO(): BuildingDAO

    abstract fun getCivilDAO(): CivilizationDAO


}