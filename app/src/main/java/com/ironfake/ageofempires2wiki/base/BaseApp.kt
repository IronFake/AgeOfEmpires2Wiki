package com.ironfake.ageofempires2wiki.base

import android.app.Application
import androidx.room.Room
import com.ironfake.ageofempires2wiki.BuildConfig
import com.ironfake.ageofempires2wiki.data.AoeDataBase
import com.ironfake.ageofempires2wiki.inkection.component.ApplicationComponent
import com.ironfake.ageofempires2wiki.inkection.component.DaggerApplicationComponent

class BaseApp : Application() {


    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }

        /** Init all dataBases in app */
        dataBase =  Room.databaseBuilder(applicationContext, AoeDataBase::class.java, "aoe_rep")
            .fallbackToDestructiveMigration().build()
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
            .build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set

        /**
         * Init dataBases with data
         */
        var dataBase: AoeDataBase? = null
    }
}