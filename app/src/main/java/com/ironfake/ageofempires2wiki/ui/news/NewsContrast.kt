package com.ironfake.ageofempires2wiki.ui.news

import com.ironfake.ageofempires2wiki.base.BaseContract
import com.ironfake.ageofempires2wiki.model.Article

class NewsContrast {

    interface View: BaseContract.View {
        fun updateNews(news: List<Article>)
        fun showError(error: String)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadNews()
    }
}