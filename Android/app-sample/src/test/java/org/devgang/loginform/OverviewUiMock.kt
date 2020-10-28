package org.devgang.loginform

class OverviewUiMock : OverviewUi {
    var noResultsFoundCalled = false
    var setItems: List<OverviewItemViewModel> = emptyList()
    var overviewItemViewModel: OverviewItemViewModel? = null

    override fun displayNoResultsFound() {
        noResultsFoundCalled = true
    }

    override fun setViewModel(overviewItemViewModel: OverviewItemViewModel) {
        this.overviewItemViewModel = overviewItemViewModel
    }
}
