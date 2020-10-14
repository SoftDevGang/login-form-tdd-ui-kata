package org.devgang.loginform

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsAdapterTest {

    @Test
    fun should_count_0_for_empty_model() {
        val newsModel = NewsModel(arrayOf())
        val newsAdapter = NewsAdapter()
        newsAdapter.setData(newsModel)

        val itemCount = newsAdapter.itemCount
        Assert.assertEquals(0, itemCount)
    }

//    @Test
//    fun should_return_empty_view_holder_for_empty_model() {
//        val newsModel = NewsModel(arrayOf())
//        val newsAdapter = NewsAdapter()
//        newsAdapter.setData(newsModel)
//
//        val viewHolder = newsAdapter.onCreateViewHolder()
//        viewHolder.itemViewType
//        newsAdapter.bindViewHolder(viewHolder,0)
//        Assert.assertEquals(0, itemCount)
//    }
}

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var newsModel = NewsModel(emptyArray())
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if(newsModel.items.isEmpty())
        TODO()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO()
    }

    override fun getItemCount(): Int {
        return newsModel.items.size
    }

    fun setData(newsModel: NewsModel) {
        this.newsModel = newsModel
    }

}