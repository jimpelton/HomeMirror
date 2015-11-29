package com.morristaedt.mirror.aspect;

import com.morristaedt.mirror.receiver.*;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Property 9
 * The number of data refreshes initiated by AlarmReceiver may not exceed ten minute intervals.
 *
 * Created by jim on 11/28/15.
 */
@Aspect
public class AlarmReceiverBroadcastAspect {
    private static final String TAG = AlarmReceiver.class.getName();

    private static long lastCallTime = 0L;

    @After("execution(* AlarmReceiver.onReceive(Context, Intent))")
    public void afterAlarmReceiverOnReceive() {
        long now = System.currentTimeMillis();
        long diff = now-lastCallTime;
        if (diff < (10*60*1000)) {
            Log.e(TAG, "AlarmReceiver executed before threshold: " + diff + "ms.");
        } else {
            Log.d(TAG, "AlarmReceiver broadcast  intent.");
        }

        lastCallTime = now;
    }
}
