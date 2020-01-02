package com.ironfake.ageofempires2wiki.inkection.component

import com.ironfake.ageofempires2wiki.inkection.module.ActivityModule
import com.ironfake.ageofempires2wiki.ui.main.MainActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}