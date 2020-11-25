package org.devgang.loginform

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.devgang.loginform.network.NewsDownload
import org.devgang.loginform.view.OverviewItemViewModel
import org.devgang.loginform.view.OverviewUi

class NewsReaderOverviewPresenter(
    private val overviewUi: OverviewUi,
    private val newsDownload: NewsDownload,
    private val schedulerToSubscribe: Scheduler = Schedulers.io(),
    private val schedulerToObserve: Scheduler = AndroidSchedulers.mainThread()
) {
    private val compositeDisposable = CompositeDisposable()

    fun onLoad() {
        val newsModelObservable = newsDownload.newsUpdates()
        val subscribe = newsModelObservable
            .subscribeOn(schedulerToSubscribe)
            .observeOn(schedulerToObserve)
            .subscribe { newsModel ->
                // TODO separate RX from logic: make this a simple function again
                // move out the scheduler things and the download. When download is finished
                // this method is called
                // TODO separate RX from logic: create download chain function somewhere?
                if (newsModel.items.isEmpty()) {
                    overviewUi.displayNoResultsFound()
                } else {
                    val items = newsModel.items.map { OverviewItemViewModel(it.title) }
                    overviewUi.setViewModel(items)
                }
            }
        compositeDisposable.add(subscribe)
    }

    fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
