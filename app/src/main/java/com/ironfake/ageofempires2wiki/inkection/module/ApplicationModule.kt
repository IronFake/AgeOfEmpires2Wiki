package com.ironfake.ageofempires2wiki.inkection.module

import android.app.Application
import com.ironfake.ageofempires2wiki.base.BaseApp
import com.ironfake.ageofempires2wiki.inkection.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}