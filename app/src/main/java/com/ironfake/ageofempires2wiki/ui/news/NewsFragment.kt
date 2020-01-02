package com.ironfake.ageofempires2wiki.ui.news

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
import com.ironfake.ageofempires2wiki.databinding.FragmentNewsBinding
import com.ironfake.ageofempires2wiki.inkection.component.DaggerFragmentComponent
import com.ironfake.ageofempires2wiki.model.Article
import javax.inject.Inject

class NewsFragment : Fragment(), NewsContrast.View{


    @Inject
    lateinit var presenter: NewsContrast.Presenter

    /**
     * DataBinding instance
     */
    private lateinit var binding: FragmentNewsBinding

    /**
     * The adapter for the list of posts
     */
    private lateinit var postsAdapter : NewsAdapter

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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news,  container, false)
        postsAdapter = NewsAdapter(context)
        binding.adapter = postsAdapter
        binding.layoutManager = LinearLayoutManager(context)
        binding.dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        presenter.loadNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun updateNews(news: List<Article>) {
        postsAdapter.updateNews(news)
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
}
