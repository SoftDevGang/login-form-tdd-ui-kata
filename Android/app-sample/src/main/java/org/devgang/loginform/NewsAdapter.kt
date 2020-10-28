package org.devgang.loginform

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {
    private var overviewItemViewModel: OverviewItemViewModel = OverviewItemViewModel()
    private val NO_NEWS = 1
    private val NEWS = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            NEWS ->
                return NewsItemViewHolder(layoutInflater.inflate(R.layout.item_news, parent, false))
            NO_NEWS ->
                return NoNewsItemViewHolder(layoutInflater.inflate(R.layout.no_news, parent, false))
        }
        throw IllegalArgumentException("Invalid view type")
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is NoNewsItemViewHolder -> holder.bind("NO")
            is NewsItemViewHolder -> holder.bind(overviewItemViewModel.newsModel.items[position])
            else -> throw IllegalArgumentException("Invalid ViewHolder")
        }
    }

    override fun getItemCount(): Int {
        return overviewItemViewModel.newsModel.items.size
    }

    fun setData(overviewItemViewModel: OverviewItemViewModel) {
        this.overviewItemViewModel = overviewItemViewModel
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return overviewItemViewModel.modelType
    }
}