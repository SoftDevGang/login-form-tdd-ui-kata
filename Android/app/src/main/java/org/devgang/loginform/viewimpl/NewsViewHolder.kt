package org.devgang.loginform.viewimpl

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_news.view.*

class NewsViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    fun setTitle(title: String) {
        view.newsTitle.text = title
    }
}
