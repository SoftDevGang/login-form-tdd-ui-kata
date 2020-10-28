package org.devgang.loginform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsOverviewActivity : AppCompatActivity(), OverviewUi {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_overview)
        findViewById<TextView>(R.id.newsPageHeadline).text = "Today's News!"

        val newsListView = findViewById<RecyclerView>(R.id.newsContainer)
        newsListView.layoutManager = LinearLayoutManager(this)
        newsListView.adapter = NewsAdapter()
    }

    override fun displayNoResultsFound() {
    }

    override fun setViewModel(items: List<OverviewItemViewModel>) {
    }
}
