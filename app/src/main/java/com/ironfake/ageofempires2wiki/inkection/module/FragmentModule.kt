package com.ironfake.ageofempires2wiki.inkection.module

import com.ironfake.ageofempires2wiki.api.ApiServiceInterface
import com.ironfake.ageofempires2wiki.ui.Units.UnitsContrast
import com.ironfake.ageofempires2wiki.ui.Units.UnitsPresenter
import com.ironfake.ageofempires2wiki.ui.civil.CivilContrast
import com.ironfake.ageofempires2wiki.ui.civil.CivilPresenter
import com.ironfake.ageofempires2wiki.ui.news.NewsContrast
import com.ironfake.ageofempires2wiki.ui.news.NewsPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideCivikPresenter(): CivilContrast.Presenter {
        return CivilPresenter()
    }

    @Provides
    fun provideUnitsPresenter(): UnitsContrast.Presenter {
        return UnitsPresenter()
    }

    @Provides
    fun provideNewsPresenter(): NewsContrast.Presenter {
        return NewsPresenter()
    }

    @Provides
    fun provideNewsApi(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}