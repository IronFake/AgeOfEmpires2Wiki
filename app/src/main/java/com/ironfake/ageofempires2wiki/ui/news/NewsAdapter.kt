package com.ironfake.ageofempires2wiki.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.databinding.ItemNewsBinding
import com.ironfake.ageofempires2wiki.model.Article
import com.ironfake.ageofempires2wiki.model.News

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    /**
     * The list of posts of the adapter
     */
    private var newsList: List<Article> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemNewsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_news,
                parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.bind(newsList[position])
    }

    /**
     * Updates the list of posts of the adapter
     * @param posts the new list of posts of the adapter
     */
    fun updateNews(news: List<Article>) {
        this.newsList = news
        notifyDataSetChanged()
    }

    class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){

        /**
         * Binds a news into the view
         */
        fun bind(news: Article) {
            binding.news = news
            binding.executePendingBindings()
        }
    }
}