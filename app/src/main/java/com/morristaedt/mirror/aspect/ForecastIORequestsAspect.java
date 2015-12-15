package com.morristaedt.mirror.aspect;

import com.morristaedt.mirror.modules.*;
import com.morristaedt.mirror.requests.*;
import android.util.Log;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Property 8
 * If REST requests to https://api.forecast.io exceed 1000 requests in one day, then no
 * further requests are made.
 *
 *
 * The 1000 requests comes from the limit the site places on free api requests.
 *
 *
 * As is, this aspect represents more of an academic experiment rather than anything that would
 * ever be put into production (actually, most of these aspects probably do...kinda sad).
 * This test does not consider the success of the query, only that an attempt was made. If this
 * code were to be put into production, likely we don't want to count non-successful requests
 * (such as if the internet was down).
 *
 *
 * Likely this 1000 request limit will never be reached because requests only happen when the AlarmReciever
 * actually broadcasts a time-to-update signal, which is hardcoded at 10 minutes--but perhaps this
 * could provide a useful regression test (in case the limit got changed inadvertently).
 *
 */
@Aspect
public class ForecastIORequestsAspect {
    private static final String TAG = ForecastIORequestsAspect.class.getName();

    /** The time 24 hours from now */
    public static long endTimeMillis = System.currentTimeMillis() + (24*3600*1000);

    /** Num requests made. */
    private static int numRequests = 0;
    private static final int MAX_FORECAST_IO_REQUESTS = 1000;

    @After("execution(* android.os.AsyncTask.onPostExecute(ForecastResponse))")
    public void afterGetHourlyForecast() {
        numRequests++;
        long now = System.currentTimeMillis();
        if (now < endTimeMillis) {
            if (numRequests > MAX_FORECAST_IO_REQUESTS) {
                Log.e(TAG, "Forecast requests over 1000 within 24 hour period: " +
                        "numRequests: " + numRequests + " time: " + now +
                        " endTime: " + endTimeMillis);
            }
        } else {
            // reset the clock, now is  endTimeMillis.
            endTimeMillis = now + (24*3600*1000);
            numRequests = 1;
            Log.d(TAG, "numRequests reset to 1.");
        }
        Log.d(TAG, "Forecast request made: numRequests: " + numRequests);
    }
}
