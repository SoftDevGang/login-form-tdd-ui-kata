package org.devgang.loginform.viewimpl

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.devgang.loginform.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsOverviewActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<NewsOverviewActivity>()

    /*
     * https://stackoverflow.com/questions/24517291/get-current-activity-in-espresso-android
     */
    private fun getActivity(): Activity? {
        var activity: Activity? = null
        activityScenarioRule.scenario.onActivity {
            activity = it
        }
        return activity
    }

    // --- get started with basic UI test

    @Test
    fun should_show_news_headline() {
        onView(withId(R.id.newsPageHeadline)).check(matches(withText("Today's News!")))
    }

    // --- test working UI for view methods (presenter -> view)

    @Test
    fun should_display_nothing_found() {
        val newsOverviewActivity = getActivity() as NewsOverviewActivity
        newsOverviewActivity.displayNoResultsFound()
        // val recyclerView: RecyclerView = newsOverviewActivity.findViewById(R.id.newsContainer)
        // Assert.assertEquals(1,recyclerView.adapter!!.itemCount)
        // This is correct but does not help to display something - redo test and go deeper

        // TODO: UI is working as expected, but view matching is not correct
        onView(withId(R.id.newsContainer)).check(matches(withText("No news")))
    }
}
