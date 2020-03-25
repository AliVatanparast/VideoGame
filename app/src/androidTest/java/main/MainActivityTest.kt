package main

import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import vgame.ir.R

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val rule = MainActivityTest()

    @Test
    fun nav_button_change_fragments_success() {
        Log.e("@Test", "Performing login success test")
        /*Espresso.onView((withId(R.id.edt_mobile)))
                .perform(ViewActions.typeText(username_tobe_typed))

        Espresso.onView(withId(R.id.password))
                .perform(ViewActions.typeText(correct_password))

        Espresso.onView(withId(R.id.login_button))
                .perform(ViewActions.click())

        Espresso.onView(withId(R.id.login_result))
                .check(matches(withText(R.string.login_success)))*/

        Espresso.onView(withId(R.id.btn_profile))
                .perform(ViewActions.click())

        Espresso.onView(withId(R.id.txt_tiket))
                .check(matches(isDisplayed()))
    }
}