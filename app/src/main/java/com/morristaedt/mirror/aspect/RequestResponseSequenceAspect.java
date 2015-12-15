package com.morristaedt.mirror.aspect;

import com.morristaedt.mirror.modules.*;
import com.morristaedt.mirror.requests.ForecastResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jim on 11/28/15.
 * 10. A request from the NewsModule must have been sent prior to a response from the
 *     BBC RSS feed (sequential).
 *     (a) If (Pre): NewsModule.getNewsHeadline.onPostExecute() is called
 *     (b) Then (Post): NewsModule.getNewsHeadline.doInBackground() was called prior.
 * 11. A request from the ForecastModule must have been sent prior to a response from the
 *     forecast.io feed (sequential).
 *     (a) If (Pre): ForecastModule.getHourlyForecast.onPostExecute() is called
 *     (b) Then (Post): ForecastModule.getHourlyForecast.doInBackground() was called prior
 * 12. A request from the XKCDModule must have been sent prior to a response from the XKCD feed
 *     (sequential).
 *     (a) If (Pre): XKCDModule.getXKCDForToday.onPostExecute() is called
 *     (b) Then (Post): XKCDModule.getXKCDForToday.doInBackground() was called prior
 * 13. A request from the YahooFinanceModule must have been sent prior to a response from the Yahoo
 *     Finance feed (sequential).
 *     (a) If (Pre): YahooFinanceModule.getStockForToday.onPostExecute() is called
 *     (b) Then (Post): YahooFinanceModule.getStockForToday.doInBackground() was called prior
*/

@Aspect
public class RequestResponseSequenceAspect {
    public static final String TAG = RequestResponseSequenceAspect.class.toString();

    /**
     * I think, that the way android executor service works is single threaded? And, that this
     * should never get more than one long. But maybe...not...must read more SDK code!
     */
    private static List<Object> tasks = Collections.synchronizedList(new ArrayList<>());

    /**
     *  onPostExecute()
     *  With any return type and taking an argument with typename ending in Response.
     */
    @Pointcut("execution(* android.os.AsyncTask.onPostExecute(com.morristaedt.mirror.requests.*Response)) " +
            "&& args(response)")
    public void onPostExecutePointcut(Object response){ }

    /**
     * AsyncTask+.doInBackground(*)
     * Used for matching anonymous intances of AsyncTask in the updater modules.
     *
     * doInBackground() in subclass of AsyncTask,
     * with return typename ending in Response,
     * and taking any arguments.
     */
    @AfterReturning(pointcut="execution(com.morristaedt.mirror.requests.*Response " +
                             "android.os.AsyncTask+.doInBackground(*))",
                    returning="response")
    public void afterDoInBackgroundPointcut(JoinPoint joinPoint, Object response) {
        Log.d(TAG, "inside: afterDoInBackgroundPointcut() Entering with target list: " + tasks.toString());

        if (response != null) {
            tasks.add(response);
        } else {
            Log.d(TAG, "inside: afterDoInBackgroundPointcut(): response was null.");
        }

        Log.d(TAG, "inside: afterDoInBackgroundPointcut() Exiting with target list: " + tasks.toString());
    }


    /**
     * Before onPostExecute() is executed, check to see if response was the one generated
     * during execution of doInBackground(). If yes, remove the response.
     */
    @Before("onPostExecutePointcut(response)")
    public void afterOnPostExecute(JoinPoint joinPoint, Object response) {
        Log.d(TAG, "inside: afterOnPostExecute() Entering with target list: " + tasks.toString());
        if (tasks.contains(response)) {
            Log.d(TAG, "Yay! " + response + " visited here recently!");
            tasks.remove(response);
        } else {
            Log.e(TAG, "Response " + response + " was not inserted into responses list!");
        }
        Log.d(TAG, "inside: afterOnPostExecute() Exiting with target list: " + tasks.toString());
    }

}
