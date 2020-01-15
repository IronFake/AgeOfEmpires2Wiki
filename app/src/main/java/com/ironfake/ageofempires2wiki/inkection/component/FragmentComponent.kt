package com.ironfake.ageofempires2wiki.inkection.component

import com.ironfake.ageofempires2wiki.inkection.module.FragmentModule
import com.ironfake.ageofempires2wiki.ui.building.BuildingFragment
import com.ironfake.ageofempires2wiki.ui.buildingList.BuildingListFragment
import com.ironfake.ageofempires2wiki.ui.civil.CivilFragment
import com.ironfake.ageofempires2wiki.ui.civilList.CivilListFragment
import com.ironfake.ageofempires2wiki.ui.newsList.NewsFragment
import com.ironfake.ageofempires2wiki.ui.unit.UnitFragment
import com.ironfake.ageofempires2wiki.ui.unitsList.UnitListFragment
import dagger.Component

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(civilListFragment: CivilListFragment)

    fun inject(civilFragment: CivilFragment)

    fun inject(unitListFragment: UnitListFragment)

    fun inject(unitFragment: UnitFragment)

    fun inject(newsListFragment: NewsFragment)

    fun inject(buildingListFragment: BuildingListFragment)

    fun inject(buildingFragment: BuildingFragment)
}