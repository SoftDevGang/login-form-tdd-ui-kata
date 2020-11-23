package org.devgang.loginform.viewimpl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.devgang.loginform.R
import org.devgang.loginform.view.OverviewItemViewModel

/**
 * internal class to the UI = View.
 */
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_NO_NEWS = 0
        const val VIEW_TYPE_NEWS = 1
    }

    private var newsModel = emptyList<OverviewItemViewModel>()

    override fun getItemViewType(position: Int): Int {
        return if (isEmpty()) VIEW_TYPE_NO_NEWS else VIEW_TYPE_NEWS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // TODO warning: lift return out of if
        if (viewType == VIEW_TYPE_NO_NEWS) {
            return NoNewsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_nonews, parent, false)
            )
        } else {
            return NewsViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_news, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_NEWS) {
            (holder as NewsViewHolder).setTitle(newsModel[position].title)
        }
    }

    override fun getItemCount(): Int {
        return if (isEmpty()) 1 else newsModel.size
    }

    private fun isEmpty() = newsModel.isEmpty()

    fun setData(newsModel: List<OverviewItemViewModel>) {
        this.newsModel = newsModel
        notifyDataSetChanged()
    }
}
