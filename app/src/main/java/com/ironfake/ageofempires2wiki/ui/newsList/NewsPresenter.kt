package com.ironfake.ageofempires2wiki.ui.newsList

import android.content.Context
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.api.ApiServiceInterface
import com.ironfake.ageofempires2wiki.utils.NEWS_API_KEY
import com.ironfake.ageofempires2wiki.utils.NEWS_BASE_URL
import com.ironfake.ageofempires2wiki.utils.NEWS_QUERY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class NewsPresenter : NewsContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create(NEWS_BASE_URL)
    private lateinit var view: NewsContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: NewsContract.View, context: Context) {
        this.view = view
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    override fun loadNews() {
        view.showLoading()
        val subscribe  = api
            .getNews(NEWS_QUERY, NEWS_API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { newsList -> if (newsList.news != null){
                    view.updateNews(newsList.news?: return@subscribe)
                }},
                { view.showError(R.string.unknown_error.toString())}
            )
        subscriptions.add(subscribe)
    }
}