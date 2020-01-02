package com.ironfake.ageofempires2wiki.inkection.component

import com.ironfake.ageofempires2wiki.inkection.module.FragmentModule
import com.ironfake.ageofempires2wiki.ui.Units.UnitsFragment
import com.ironfake.ageofempires2wiki.ui.civil.CivilFragment
import com.ironfake.ageofempires2wiki.ui.news.NewsFragment
import dagger.Component

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(civilFragment: CivilFragment)

    fun inject(unitsFragment: UnitsFragment)

    fun inject(listFragment: NewsFragment)
}