package org.devgang.loginform

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.devgang.loginform.model.NewsDownload
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
        val newsModelObservable = newsDownload.downloadNews()
        val subscribe = newsModelObservable
            .subscribeOn(schedulerToSubscribe)
            .observeOn(schedulerToObserve) // TODO Inject Scheduling, test is blocking
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
