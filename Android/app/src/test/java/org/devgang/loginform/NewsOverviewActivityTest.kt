package org.devgang.loginform

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Ignore
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

    @Test
    fun should_show_news_headline() {
        onView(withId(R.id.newsPageHeadline)).check(matches(withText("Today's News!")))
    }

    @Test @Ignore
    fun should_display_nothing_found() {
        val newsOverviewActivity = getActivity() as NewsOverviewActivity
        newsOverviewActivity.displayNoResultsFound()
        val recyclerView: RecyclerView = newsOverviewActivity.findViewById(R.id.newsContainer)
        Assert.assertEquals(recyclerView.adapter!!.itemCount, 1)
    }
}