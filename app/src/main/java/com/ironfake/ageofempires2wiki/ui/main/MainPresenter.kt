package com.ironfake.ageofempires2wiki.ui.main

import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.ui.news.NewsFragment
import io.reactivex.disposables.CompositeDisposable

class MainPresenter : MainContrast.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContrast.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContrast.View) {
        this.view = view
        view.setFragment(NewsFragment())
    }

    override fun addFragment(fragment: Fragment) {
        view.setFragment(fragment)
    }
}