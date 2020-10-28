package org.devgang.loginform

import android.view.View
import android.widget.TextView

class NewsItemViewHolder(itemView: View) : BaseViewHolder<NewsItem>(itemView) {
    override fun bind(item: NewsItem) {
        itemView.findViewById<TextView>(R.id.newsTitle).text = item.title
    }
}
