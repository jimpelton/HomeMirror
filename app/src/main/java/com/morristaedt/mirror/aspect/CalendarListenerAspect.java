package com.morristaedt.mirror.aspect;

import android.util.Log;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Property 3
 *
 * If ConfigurationSettings.mShowNextCalendarEvent is set to true,
 * then the next calendar event view is displayed with the next calendar event.
 *      (a) If (Pre): ConfigurationSettings.mShowNextCalendarEvent() == true
 *      (b) Then (Post): CalendarModule.CalendarListener.onCalendarUpdate(String title, String details)
 *          is called with the title and details of the next calendar event.
 */
@Aspect
public class CalendarListenerAspect {
    public static final String TAG = CalendarListenerAspect.class.toString();

    @After("execution(* com.morristaedt.mirror.modules.CalendarModule.CalendarListener+.onCalendarUpdate(String, String))" +
            "&& args(s0, s1)")
    public void afterCalendarListenerOnCalendarUpdate(String s0, String s1) {
        Log.d(TAG, "onCalendarUpdate() got called: " + s0 + s1);
    }


}
