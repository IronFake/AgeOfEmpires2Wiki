package com.ironfake.ageofempires2wiki.ui.news

import android.util.Log
import androidx.annotation.StringRes
import com.ironfake.ageofempires2wiki.base.BaseView
import com.ironfake.ageofempires2wiki.model.Article
import com.ironfake.ageofempires2wiki.model.News

/**
 * Interface providing required method for a view displaying news
 */
interface NewsView : BaseView{

    /**
     * Updates the previous news by the specified ones
     * @param news the list of news that will replace existing ones
     */
    fun updateNews(news: List<Article>)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

    /**
     * Displays an error in the view
     * @param errorResId the resource id of the error to display in the view
     */
    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()
}