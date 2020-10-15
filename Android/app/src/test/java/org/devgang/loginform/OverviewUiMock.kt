package org.devgang.loginform

class OverviewUiMock : OverviewUi {
    var noResultsFoundCalled = false
    var setItems: List<OverviewItemViewModel> = emptyList()

    override fun displayNoResultsFound() {
        noResultsFoundCalled = true
    }

    override fun setViewModel(items: List<OverviewItemViewModel>) {
        setItems = items

    }
}
