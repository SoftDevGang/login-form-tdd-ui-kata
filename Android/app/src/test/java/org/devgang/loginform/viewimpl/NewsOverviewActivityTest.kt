package org.devgang.loginform.viewimpl

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.devgang.loginform.R
import org.devgang.loginform.view.OverviewItemViewModel
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsOverviewActivityTest {

    @Before
    fun initTest() {
        NewsOverviewActivity.isTest = true
    }

    @get:Rule
    var activityScenarioRule = activityScenarioRule<NewsOverviewActivity>()

    // https://stackoverflow.com/questions/24517291/get-current-activity-in-espresso-android
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

        onView(withId(R.id.newsContainer)).check(matches(atPosition(0, withText("No news"))))
        // onView(withId(R.id.newsContainer)).check(matches(atPosition(0, hasDescendant(withText("No news")))))
    }

    @Test
    fun should_display_two_news() {
        //TODO: Test shows weird error message. Idea: not excecute on create
        val newsOverviewActivity = getActivity() as NewsOverviewActivity

        val items =
            listOf(OverviewItemViewModel("First News"), OverviewItemViewModel("Second News"))
        newsOverviewActivity.setViewModel(items)

        onView(withId(R.id.newsContainer)).check(matches(atPosition(0, hasDescendant(withText("First News")))))
        onView(withId(R.id.newsContainer)).check(matches(atPosition(1, hasDescendant(withText("Second News")))))
    }

    // see https://stackoverflow.com/a/34795431/104143
    private fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}
