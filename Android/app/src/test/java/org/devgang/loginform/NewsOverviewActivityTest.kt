package org.devgang.loginform

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsOverviewActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<NewsOverviewActivity>()

    @Test
    fun should_show_news_headline() {
        onView(withId(R.id.newsPageHeadline)).check(matches(withText("Today's News!")))
    }
}