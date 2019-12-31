package com.ironfake.ageofempires2wiki.base

import com.ironfake.ageofempires2wiki.inkection.component.DaggerPresenterInjector
import com.ironfake.ageofempires2wiki.inkection.component.PresenterInjector
import com.ironfake.ageofempires2wiki.inkection.module.ContextModule
import com.ironfake.ageofempires2wiki.inkection.module.NetworkModule
import com.ironfake.ageofempires2wiki.ui.news.NewsPresenter

abstract class BasePresenter<out V: BaseView> (protected val view: V){

    /**
     * The injector used to inject required dependencies
     */
    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()


    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject(){
        when (this) {
            is NewsPresenter -> injector.inject(this)
        }
    }
}