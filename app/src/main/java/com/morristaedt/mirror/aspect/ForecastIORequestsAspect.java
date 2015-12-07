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
 * Created by jim on 11/28/15.
 */
@Aspect
public class ForecastIORequestsAspect {
    private static final String TAG = ForecastIORequestsAspect.class.getName();

    public static long endTimeMillis = System.currentTimeMillis() + (24*3600*1000);

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
