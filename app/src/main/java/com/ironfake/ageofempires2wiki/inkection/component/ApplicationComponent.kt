package com.ironfake.ageofempires2wiki.inkection.component

import com.ironfake.ageofempires2wiki.base.BaseApp
import com.ironfake.ageofempires2wiki.inkection.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApp)
}