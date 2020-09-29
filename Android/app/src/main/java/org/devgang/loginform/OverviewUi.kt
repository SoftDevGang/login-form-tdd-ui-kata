package org.devgang.loginform

interface OverviewUi {
    fun displayNoResultsFound()
    fun setViewModel(items: List<OverviewItemViewModel>)
}