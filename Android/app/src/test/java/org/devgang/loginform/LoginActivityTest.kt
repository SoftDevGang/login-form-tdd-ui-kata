package org.devgang.loginform

import android.os.Build
import android.text.InputFilter
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<LoginActivity>()

    @Test
    fun `has username field with max length of 20`() {
        onView(withId(R.id.username))
            .check(matches(checkMaxLength(20)))
    }

    @Test
    fun `has label for username`() {
        onView(withId(R.id.username_label))
            .check(matches(withText("Phone, email or username")))
    }

    @Test
    fun `has login button`() {
        onView(withId(R.id.login_button))
            .check(matches(withText("Log in")))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun `when username is introduced then login button is enabled`() {
        onView(withId(R.id.username))
            .perform(typeText("a real username"))

        onView(withId(R.id.login_button))
            .check(matches(isEnabled()))
    }

    private fun checkMaxLength(lines: Int): TypeSafeMatcher<View> {
        return object : TypeSafeMatcher<View>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            override fun matchesSafely(item: View): Boolean {
                val filters = (item as TextView).filters
                val lengthFilter = filters[0] as InputFilter.LengthFilter

                return lengthFilter.max == lines
            }

            override fun describeTo(description: Description) {
                description.appendText("checkMaxLength")
            }
        }
    }


}