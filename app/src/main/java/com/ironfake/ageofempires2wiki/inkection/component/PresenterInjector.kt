package com.ironfake.ageofempires2wiki.inkection.component

import com.ironfake.ageofempires2wiki.base.BaseView
import com.ironfake.ageofempires2wiki.inkection.module.ContextModule
import com.ironfake.ageofempires2wiki.inkection.module.NetworkModule
import com.ironfake.ageofempires2wiki.ui.news.NewsPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param newsPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(newsPresenter: NewsPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}