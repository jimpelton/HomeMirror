package com.morristaedt.mirror.aspect;

import android.util.Log;

import com.morristaedt.mirror.configuration.ConfigurationSettings;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;



@Aspect
public class SetUpActivityAspect {
    private static final String TAG = SetUpActivityAspect.class.getName();

    /**
     * Property 1,2
     * Test 1
     * @param settings The ConfigurationSettings instance called on.
     * @param b The boolean setIsCelsius was called with.
     */
    @AfterReturning(pointcut = "execution(public void ConfigurationSettings.setIsCelsius(boolean)) " +
            "&& this(settings) && args(b)")
    public void afterReturningSetIsCelsius(ConfigurationSettings settings, boolean b) {
        Log.d(TAG, "AfterReturning setIsCelsius(boolean) ==> boolean b: " + b);
        Log.d(TAG, "AfterReturning setIsCelsius(boolean) ==> getIsCelsius(): " + settings.getIsCelsius());

        if (b == settings.getIsCelsius()) {
            GlobalData.addResult(true, "test1: b==settings.getIsCelsius()");
        } else {
            GlobalData.addResult(false, "test1: b==settings.getIsCelsius()");
        }

        GlobalData.isCelsius = b;
    }

    @AfterReturning(pointcut = "execution(public void ConfigurationSettings.setShowBikingHint(boolean)) " +
            "&& this(settings) && args(b)")
    public void afterReturningSetShowBikingHint(ConfigurationSettings settings, boolean b) {
        Log.d(TAG, "AfterReturing setShowBikingHint(): ==> boolean b: " + b);
        GlobalData.showBikingHintChecked = b;
    }

    @AfterReturning(pointcut = "execution(public void ConfigurationSettings.setShowNextCalendarEvent(boolean)) " +
            "&& this(settings) && args(b)")
    public void afterReturningSetShowNextCalendarEvent(ConfigurationSettings settings, boolean b) {
        Log.d(TAG, "AfterReturing setShowNextCalendarEvent(): ==> boolean b: " + b);
        GlobalData.showNextCalendarEventChecked = b;
    }

    @AfterReturning(pointcut = "execution(public void ConfigurationSettings.setShowNewsHeadline(boolean)) " +
            "&& this(settings) && args(b)")
    public void afterReturningSetShowNewsHeadline(ConfigurationSettings settings, boolean b) {
        Log.d(TAG, "AfterReturing setShowNewsHeadline(): ==> boolean b: " + b);
        GlobalData.showNewsHeadlineChecked = b;
    }

    @AfterReturning(pointcut = "execution(public void ConfigurationSettings.setXKCDPreference(boolean, boolean)) " +
            "&& this(settings) && args(show, invert)")
    public void afterReturningSetXKCDPreference(ConfigurationSettings settings, boolean show, boolean invert) {
        Log.d(TAG, "AfterReturing setXKCDPreference(): ==> boolean b: " + show);
        GlobalData.showNextCalendarEventChecked = show;
    }

    @AfterReturning(pointcut = "execution(public void ConfigurationSettings.setLatLon(String, String)) " +
            "&& this(settings) && args(lat, lon)")
    public void afterReturningSetLatLon(ConfigurationSettings settings, String lat, String lon) {
        Log.d(TAG, "AfterReturing setLatLon(): ==> lat, lon: " + lat + ", " + lon);
        GlobalData.haveLocation = (lat!=null && lon!=null && !lat.isEmpty() && !lon.isEmpty());
    }

    @AfterReturning(pointcut = "execution(public void ConfigurationSettings.setStockTickerSymbol(String)) " +
            "&& this(settings) && args(sym)")
    public void afterReturningSetShowNextCalendarEvent(ConfigurationSettings settings, String sym) {
        Log.d(TAG, "AfterReturing setShowNextCalendarEvent(): ==> " + sym);
        GlobalData.stockSymbolSet = sym!=null && !sym.isEmpty();
    }

}
