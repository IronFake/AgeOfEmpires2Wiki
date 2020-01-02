package com.ironfake.ageofempires2wiki.base

import android.app.Application
import com.ironfake.ageofempires2wiki.BuildConfig
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
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
            .build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}