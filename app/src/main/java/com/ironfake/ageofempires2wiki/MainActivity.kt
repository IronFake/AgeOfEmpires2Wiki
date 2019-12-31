package com.ironfake.ageofempires2wiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironfake.ageofempires2wiki.base.BaseActivity
import com.ironfake.ageofempires2wiki.databinding.ActivityMainBinding
import com.ironfake.ageofempires2wiki.model.Article
import com.ironfake.ageofempires2wiki.model.News
import com.ironfake.ageofempires2wiki.ui.news.NewsAdapter
import com.ironfake.ageofempires2wiki.ui.news.NewsPresenter
import com.ironfake.ageofempires2wiki.ui.news.NewsView

/**
 * Activity displaying the list of posts
 */
class MainActivity : BaseActivity<NewsPresenter>(), NewsView {

    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * The adapter for the list of posts
     */
    private val postsAdapter = NewsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = postsAdapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun instantPresenter(): NewsPresenter {
        return NewsPresenter(this)
    }

    override fun updateNews(news: List<Article>) {
        postsAdapter.updateNews(news)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }
}