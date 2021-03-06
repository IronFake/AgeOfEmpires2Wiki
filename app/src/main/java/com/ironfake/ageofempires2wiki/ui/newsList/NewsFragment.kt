package com.ironfake.ageofempires2wiki.ui.newsList

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.databinding.FragmentNewsListBinding
import com.ironfake.ageofempires2wiki.inkection.component.DaggerFragmentComponent
import com.ironfake.ageofempires2wiki.model.newsApiModels.News
import com.ironfake.ageofempires2wiki.ui.web.WebViewActivity
import javax.inject.Inject

class NewsFragment : Fragment(), NewsContract.View, NewsAdapter.onItemClickListener{


    @Inject
    lateinit var presenter: NewsContract.Presenter

    /**
     * DataBinding instance
     */
    private lateinit var binding: FragmentNewsListBinding

    /**
     * The adapter for the list of posts
     */
    private lateinit var mewsAdapter : NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val newsComponent = DaggerFragmentComponent.builder()
            .build()

        newsComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list,  container, false)
        mewsAdapter = NewsAdapter(context, this)
        binding.adapter = mewsAdapter
        binding.layoutManager = LinearLayoutManager(context)
        binding.dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this, context!!)
        presenter.subscribe()
        presenter.loadNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateNews(news: List<News>) {
        mewsAdapter.updateNews(news)
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun itemOnClick(news: News) {
        val i = Intent(context, WebViewActivity::class.java)
        i.data = Uri.parse(news.url.toString())
        i.action = Intent.ACTION_VIEW
        startActivity(i)
    }
}
