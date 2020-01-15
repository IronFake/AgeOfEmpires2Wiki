package com.ironfake.ageofempires2wiki.ui.newsList

import com.ironfake.ageofempires2wiki.base.BaseContract
import com.ironfake.ageofempires2wiki.model.newsApiModels.News

class NewsContract {

    interface View: BaseContract.View {
        fun updateNews(news: List<News>)
        fun showError(error: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadNews()
    }
}