package org.devgang.loginform

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_NO_NEWS = 0
        const val VIEW_TYPE_NEWS = 1
    }

    private var newsModel = NewsModel(emptyArray())

    override fun getItemViewType(position: Int): Int {
        return if(isEmpty()) VIEW_TYPE_NO_NEWS else VIEW_TYPE_NEWS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return NoNewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_nonews, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return if (isEmpty()) 1 else newsModel.items.size
    }

    private fun isEmpty() = newsModel.items.isEmpty()

    fun setData(newsModel: NewsModel) {
        this.newsModel = newsModel
    }
}
