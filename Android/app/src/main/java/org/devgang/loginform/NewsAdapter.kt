package org.devgang.loginform

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var newsModel = NewsModel(emptyArray())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if(newsModel.items.isEmpty())
        TODO()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO()
    }

    override fun getItemCount(): Int {
        return if (newsModel.items.isEmpty()) 1 else newsModel.items.size
    }

    fun setData(newsModel: NewsModel) {
        this.newsModel = newsModel
    }
}
