package com.ironfake.ageofempires2wiki.ui.news

import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.api.ApiServiceInterface
import com.ironfake.ageofempires2wiki.utils.API_KEY
import com.ironfake.ageofempires2wiki.utils.QUERY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class NewsPresenter : NewsContrast.Presenter{

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: NewsContrast.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: NewsContrast.View) {
        this.view = view
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    override fun loadNews() {
        view.showLoading()
        val subscribe  = api
            .getNews(QUERY, API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { newsList -> if (newsList.articles != null){
                    view.updateNews(newsList.articles?: return@subscribe)
                }},
                { view.showError(R.string.unknown_error.toString())}
            )
        subscriptions.add(subscribe)
    }
}