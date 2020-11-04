package org.devgang.loginform

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.devgang.loginform.model.NewsDownload
import org.devgang.loginform.view.OverviewItemViewModel
import org.devgang.loginform.view.OverviewUi

class NewsReaderOverviewPresenter(
    private var overviewUi: OverviewUi,
    private var newsDownload: NewsDownload
) {

    private val compositeDisposable = CompositeDisposable()

    fun onLoad() {
        val newsModelObservable = newsDownload.downloadNews()
        val subscribe = newsModelObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) // TODO Inject Scheduling, test is blocking
            .subscribe { newsModel ->
            if (newsModel.items.isEmpty()) {
                overviewUi.displayNoResultsFound()
            } else {
                val items = newsModel.items.map { OverviewItemViewModel(it.title) }
                overviewUi.setViewModel(items)
            }
        }
        compositeDisposable.add(subscribe)
    }
    // TODO dispose disposables
}
