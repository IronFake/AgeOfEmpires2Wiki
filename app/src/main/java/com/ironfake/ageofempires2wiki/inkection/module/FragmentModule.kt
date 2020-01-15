package com.ironfake.ageofempires2wiki.inkection.module

import com.ironfake.ageofempires2wiki.api.ApiServiceInterface
import com.ironfake.ageofempires2wiki.ui.building.BuildingContact
import com.ironfake.ageofempires2wiki.ui.building.BuildingPresenter
import com.ironfake.ageofempires2wiki.ui.buildingList.BuildingListContract
import com.ironfake.ageofempires2wiki.ui.buildingList.BuildingListPresenter
import com.ironfake.ageofempires2wiki.ui.civil.CivilContract
import com.ironfake.ageofempires2wiki.ui.civil.CivilPresenter
import com.ironfake.ageofempires2wiki.ui.civilList.CivilListContract
import com.ironfake.ageofempires2wiki.ui.civilList.CivilListPresenter
import com.ironfake.ageofempires2wiki.ui.newsList.NewsContract
import com.ironfake.ageofempires2wiki.ui.newsList.NewsPresenter
import com.ironfake.ageofempires2wiki.ui.unit.UnitContact
import com.ironfake.ageofempires2wiki.ui.unit.UnitPresenter
import com.ironfake.ageofempires2wiki.ui.unitsList.UnitListContract
import com.ironfake.ageofempires2wiki.ui.unitsList.UnitListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideCivilListPresenter(): CivilListContract.Presenter {
        return CivilListPresenter()
    }

    @Provides
    fun provideCivilPresenter(): CivilContract.Presenter {
        return CivilPresenter()
    }

    @Provides
    fun provideUnitListPresenter(): UnitListContract.Presenter {
        return UnitListPresenter()
    }

    @Provides
    fun provideUnitPresenter(): UnitContact.Presenter {
        return UnitPresenter()
    }

    @Provides
    fun provideBuildingListPresenter(): BuildingListContract.Presenter {
        return BuildingListPresenter()
    }

    @Provides
    fun provideBuildingPresenter(): BuildingContact.Presenter {
        return BuildingPresenter()
    }

    @Provides
    fun provideNewsListPresenter(): NewsContract.Presenter {
        return NewsPresenter()
    }

    @Provides
    fun provideNewsApi(url: String): ApiServiceInterface {
        return ApiServiceInterface.create(url)
    }
}