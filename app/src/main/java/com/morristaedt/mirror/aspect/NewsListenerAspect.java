package com.morristaedt.mirror.aspect;

import android.util.Log;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Property 4
 *
 * If ConfigurationSettings.mShowNewsHeadline is set, then the first article in the RSS feed
 * from bbc.co.uk is displayed in the news headline view.
 *      (a) If (Pre): ConfigurationSettings.mShowNewsHeadline == true
 *      (b) Then (Post): NewsModule.NewsListener.onPostExecute(String s) is called
 *
 */
@Aspect
public class NewsListenerAspect {

    public static final String TAG = NewsListenerAspect.class.toString();

    @After("execution(* com.morristaedt.mirror.modules.NewsModule.NewsListener+.onNewNews(String)) " +
            "&& args(s)")
    public void afterOnNewNews(String s) {
        Log.d(TAG, "onNewNews() got called: " + s);
    }
}
