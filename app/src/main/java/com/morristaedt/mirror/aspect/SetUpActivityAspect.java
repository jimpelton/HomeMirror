package com.morristaedt.mirror.aspect;

import android.util.Log;


import com.morristaedt.mirror.configuration.ConfigurationSettings;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;


@Aspect
public class SetUpActivityAspect {
    /**
     * Simple test aspect.
     */

    private static final String TAG = SetUpActivityAspect.class.getName();

//    @Pointcut("execution(private void com.morristaedt.mirror.configuration.ConfigurationSettings.readPrefs())" +
//            "&& this(settings)")
//    public void onCreateMirrorActivity(ConfigurationSettings settings) {
//    }
//
//    @Before("onCreateMirrorActivity(settings)")
//    public void onCreateMirrorActivityBefore(ConfigurationSettings settings, JoinPoint joinPoint) {
//        Log.d(TAG, "Before Advice ==>  : " + Arrays.toString(joinPoint.getArgs()));
//    }
//
//    @Around("onCreateMirrorActivity(settings)")
//    public void onCreateMirrorActivityAround(ConfigurationSettings settings, ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.d(TAG, "Around Advice ==>  : " + Arrays.toString(joinPoint.getArgs()));
//
//        joinPoint.proceed();
//    }
//
//    @After("onCreateMirrorActivity(settings)")
//    public void onCreateMirrorActivityAfter(ConfigurationSettings settings, JoinPoint joinPoint) {
//        Log.d(TAG, "After Advice ==> " + Arrays.toString(joinPoint.getArgs()));
//        Log.d(TAG, "After Advice ==> " + joinPoint.getTarget().toString());
//
//        Log.d(TAG, settings.getForecastUnits());
//        Log.d(TAG, settings.getLatitude());
//        Log.d(TAG, settings.getLongitude());
//        Log.d(TAG, settings.getStockTickerSymbol());
//        Log.d(TAG, ""+settings.getIsCelsius());
//
//    }

    /**
     * Property 1, 2
     *
     * @param settings The ConfigurationSettings instance called on.
     * @param b The boolean setIsCelsius was called with.
     */
    @AfterReturning(pointcut = "execution(public void ConfigurationSettings.setIsCelsius(boolean)) " +
            "&& this(settings) && args(b)")
    public void onCreateMirrorActivityAfterReturning(ConfigurationSettings settings, boolean b) {
        Log.d(TAG, "AfterReturning setIsCelsius(boolean) ==> boolean b: " + b);
        Log.d(TAG, "AfterReturning setIsCelsius(boolean) ==> getIsCelsius(): " + settings.getIsCelsius());

    }

    /**
     * Property 3
     *
     * @param settings
     * @param b
     */
    @AfterReturning(pointcut = "execution(public void ConfigurationSettings.(boolean)) " +
            "&& this(settings) && args(b)")
    public void (ConfigurationSettings settings, boolean b) {
        Log.d(TAG, "AfterReturning setIsCelsius(boolean) ==> boolean b: " + b);
        Log.d(TAG, "AfterReturning setIsCelsius(boolean) ==> getIsCelsius(): " + settings.getIsCelsius());

    }
}
