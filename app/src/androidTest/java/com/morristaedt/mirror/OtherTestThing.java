package com.morristaedt.mirror;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.*;
import com.morristaedt.mirror.aspect.GlobalData;
import com.morristaedt.mirror.configuration.ConfigurationSettings;
import com.morristaedt.mirror.modules.NewsModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

/**
 * Created by jim on 12/9/15.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class OtherTestThing {
    @Rule
    public ActivityTestRule<MirrorActivity> mirrorActivityRule =
            new ActivityTestRule<>(MirrorActivity.class);

    private static final String PREFS_MIRROR = "MirrorPrefs";

    private static final String FORECAST_UNITS = "forecast_units";
    private static final String BIKING_HINT = "biking_hint";
    private static final String USE_MOOD_DETECTION = "mood_detection";
    private static final String SHOW_CALENDAR = "show_calendar";
    private static final String SHOW_HEADLINE = "show_headline";
    private static final String SHOW_XKCD = "xkcd";
    private static final String INVERT_XKCD = "invert_xkcd";
    private static final String LAT = "lat";
    private static final String LON = "lon";
    private static final String STOCK_TICKER = "stock_ticker";
    SharedPreferences prefs = null;

    @Before
    public void setupSharedPreferences() {
        prefs = getInstrumentation().getContext().getSharedPreferences(PREFS_MIRROR, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(SHOW_HEADLINE, false);
    }


    @After
    public void tearDownSharedPreferences() {
        prefs = null;
    }

    @Test
    public void testGetNewsHeadline() {

    }

    @Test
    public void setShowNewsHeadline() {
//        ConfigurationSettings settings = new ConfigurationSettings(getInstrumentation().getContext());
//        settings.setShowNewsHeadline(false);
//        mirrorActivityRule.getActivity();
        onView(withId(R.id.news_headline)).check(matches(isDisplayed()));
//        final CountDownLatch latch = new CountDownLatch(1);
//        NewsModule.getNewsHeadline(new NewsModule.NewsListener() {
//            @Override
//            public void onNewNews(String headline) {
//                assertTrue("headline != null", headline!=null);
//                System.out.println(headline);
//                latch.countDown();
//            }
//        });
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }



}
