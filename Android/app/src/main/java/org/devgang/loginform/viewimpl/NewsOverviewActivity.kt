package org.devgang.loginform.viewimpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.devgang.loginform.NewsReaderOverviewPresenter
import org.devgang.loginform.R
import org.devgang.loginform.network.NewsNetworkDownloader
import org.devgang.loginform.view.OverviewItemViewModel
import org.devgang.loginform.view.OverviewUi

class NewsOverviewActivity : AppCompatActivity(), OverviewUi {

    private val newsAdapter = NewsAdapter()
    private lateinit var presenter: NewsReaderOverviewPresenter

    companion object {
        open var isTest = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_overview)
        findViewById<TextView>(R.id.newsPageHeadline).text = "Today's News!"

        val newsListView = findViewById<RecyclerView>(R.id.newsContainer)
        newsListView.layoutManager = LinearLayoutManager(this)
        newsListView.adapter = newsAdapter
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        initPresenter()
        super.onPostCreate(savedInstanceState)
    }

    fun initPresenter() {
        val newsDownload =
            NewsNetworkDownloader("https://appsdata.laola1.at/data/probetag/news.json")
        presenter = NewsReaderOverviewPresenter(this, newsDownload)
        if(isTest) return
        presenter.onLoad()
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    override fun displayNoResultsFound() {
    }

    override fun setViewModel(items: List<OverviewItemViewModel>) {
        newsAdapter.setData(items)
    }
}
