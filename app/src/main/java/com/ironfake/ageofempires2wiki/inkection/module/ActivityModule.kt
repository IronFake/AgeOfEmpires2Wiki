package com.ironfake.ageofempires2wiki.inkection.module

import android.app.Activity
import com.ironfake.ageofempires2wiki.ui.main.MainContrast
import com.ironfake.ageofempires2wiki.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContrast.Presenter {
        return MainPresenter()
    }
}