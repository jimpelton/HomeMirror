package com.morristaedt.mirror.aspect;

import android.util.Log;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Property 5
 *
 * If ConfigurationSettings.mShowXKCD is set, then the Xkcd comic view is shown with
 * the current xkcd comic.
 *      (a) If (Pre): ConfigurationSettings.mShowXKCD == true
 *      (b) Then (Post): XKCDModule.XKCDListerner.onPostExecute(XKCDResponse r)
 *          is called and r != null.
 *
 *
 *
 */
@Aspect
public class XKCDListenerAspect {

    public static final String TAG = XKCDListenerAspect.class.toString();

    @After("execution(* com.morristaedt.mirror.modules.XKCDModule.XKCDListener+.onNewXKCDToday(String))" +
            "&& args(s)")
    public void afterOnNewXKCDToday(String s) {
        Log.d(TAG, "onNewXKCDToday() got called: " + s);
    }

}
