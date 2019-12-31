package com.ironfake.ageofempires2wiki.ui.news

import android.util.Log
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.base.BasePresenter
import com.ironfake.ageofempires2wiki.network.NewsApi
import com.ironfake.ageofempires2wiki.utils.API_KEY
import com.ironfake.ageofempires2wiki.utils.QUERY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * The Presenter that will present the Post view.
 * @param newsView the Post view to be presented by the presenter
 * @property newsApi the API interface implementation
 * @property context the context in which the application is running
 * @property subscription the subscription to the API call
 */
class NewsPresenter(newsView: NewsView): BasePresenter<NewsView>(newsView) {

    @Inject
    lateinit var newsApi: NewsApi

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        loadNews()
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadNews() {
        view.showLoading()
        subscription = newsApi
            .getNews(QUERY, API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { newsList -> if (newsList.articles != null){
                    view.updateNews(newsList.articles?: return@subscribe)
                }
                Log.d("Tag", "Success")},
                { view.showError(R.string.unknown_error)
                    Log.d("Tag", "Error")}
            )
    }


    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}