package com.morristaedt.mirror;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.widget.CheckBox;

import com.morristaedt.mirror.configuration.ConfigurationSettings;
import com.morristaedt.mirror.requests.ForecastRequest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by jim on 12/8/15.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UiViewerTestThing {

    @Rule
    public ActivityTestRule<SetUpActivity> setupActivityRule =
            new ActivityTestRule<>(SetUpActivity.class);

    public void clickLaunchButton() {
        onView(withId(R.id.launch_button)).perform(click());
    }

    @Before
    public void clearSharedPrefs() {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("MirrorPrefs", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    /**
     * Property 1
     *
     * If ConfigurationSettings.mForecastUnits is set to ForecastRequest.UNITS_SI,
     * then the current temperature is displayed as celsius.
     *  (a) If (Pre): ConfigurationSettings.mForecastUnits == ForecastRequest.UNITS SI
     *  (b) Then (Post): ConfigurationSettings.getIsCelsius() == true
     *
     *
     * These properties had to be modified, the best we can do without changing the code to
     * access the ConfigurationSettings instance is to query the actual preferences saved by the
     * SharedPreferences instance used by both ConfigurationSettings and MirrorActivity.
     *
     */
//    @Test
//    public void celsiusIsWrittenToSharedPrefs() {
//        onView(withId(R.id.celsius)).perform(click());
//        clickLaunchButton();
//        String units = setupActivityRule.getActivity()
//                .getSharedPreferences("MirrorPrefs", Context.MODE_PRIVATE)
//                .getString("forecast_units", ForecastRequest.UNITS_US);
//        assertTrue("si".equals(units));
//    }

    /**
     * Property 2
     *
     * If ConfigurationSettings.mForecastUnits is set to UNITS_US,
     * then the current temperature is displayed as fahrenheit.
     *  (a) If (Pre): ConfigurationSettings.mForecastUnits == ForecastReques.UNITS US
     *  (b) Then (Post): ConfigurationSettings.getIsCelsius() == false
     *
     * These properties had to be modified, the best we can do without changing the code to
     * access the ConfigurationSettings instance is to query the actual preferences saved by the
     * SharedPreferences instance used by both ConfigurationSettings and MirrorActivity.
     */
//    @Test
//    public void fahrenheitIsWrittenToSharedPrefs() {
//        onView(withId(R.id.farenheit)).perform(click());
//        clickLaunchButton();
//        String units = setupActivityRule.getActivity()
//                .getSharedPreferences("MirrorPrefs", Context.MODE_PRIVATE)
//                .getString("forecast_units", ForecastRequest.UNITS_US);
//        assertTrue("us".equals(units));
//    }

//    @Before
//    public void before() {
//        getInstrumentation().getTargetContext().getSharedPreferences("MirrorPrefs", Context.MODE_PRIVATE).edit().clear().commit();
//    }

    @Test
    public void checkNewsViewIsNotVisibleWhenHeadLineCheckBoxIsNotChecked() {
//        CheckBox box = (CheckBox) setupActivityRule.getActivity().findViewById(R.id.headline_checkbox);
//        box.setChecked(true);
//        box.setChecked(false);
        clickLaunchButton();
        try {
            onView(withId(R.id.news_headline)).check(matches(isDisplayed()));
        } catch(Exception e) {
            fail("Newsheadline was not visible.");
        }
    }

//    @Test
//    public void checkNewsViewIsVisibleWhenHeadLineCheckBoxIsChecked() {
//        SharedPreferences.Editor editor = getContext().getSharedPreferences("MirrorPrefs", Context.MODE_PRIVATE).edit();
//        editor.clear();
//        editor.commit();
//        CheckBox box = (CheckBox) setupActivityRule.getActivity().findViewById(R.id.headline_checkbox);
//        box.setChecked(true);
//        clickLaunchButton();
//
//        try {
//            onView(withId(R.id.news_headline)).check(matches(isDisplayed()));
//        } catch(Exception e) {
//            fail("Newsheadline was not visible.");
//        }
//    }


}
