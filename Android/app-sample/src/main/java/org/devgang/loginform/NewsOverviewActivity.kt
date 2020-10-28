package org.devgang.loginform

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsOverviewActivity : AppCompatActivity(), OverviewUi {
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_overview)
        newsAdapter = NewsAdapter()
        findViewById<TextView>(R.id.newsPageHeadline).text = "Today's News!"
        val recyclerView: RecyclerView = findViewById(R.id.newsContainer)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = newsAdapter
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        NewsReaderOverviewPresenter(this).createDummyData(false)
        super.onPostCreate(savedInstanceState)
    }

    override fun displayNoResultsFound() {
    }

    override fun setViewModel(overviewItemViewModel: OverviewItemViewModel) {
        newsAdapter.setData(overviewItemViewModel)
    }
}