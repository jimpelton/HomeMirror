package com.morristaedt.mirror.aspect;

import android.util.Log;
import java.util.List;
import android.os.AsyncTask;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ShouldBikeTodayAspect {
    public static final String TAG = ShouldBikeTodayAspect.class.toString();


    @AfterReturning(pointcut="execution(boolean " +
            "android.os.AsyncTask+.shouldBikeToday(java.util.List<com.morristaedt.mirror.requests.ForecastResponse.Hour>))",
            returning="shouldbike")
    public void afterShouldBikeTodayPointcut(boolean shouldbike) {
        Log.d(TAG, "afterShouldBikeTodayPointcut: " + shouldbike);

    }

}
