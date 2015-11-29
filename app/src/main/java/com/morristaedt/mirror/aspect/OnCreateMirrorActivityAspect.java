package com.morristaedt.mirror.aspect;

import android.util.Log;
import android.widget.Button;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Simple test aspect.
 */

@Aspect
public class OnCreateMirrorActivityAspect {
    private static final String TAG = OnCreateMirrorActivityAspect.class.getName();

//    @Pointcut("execution(* android.view.View.OnClickListener.onClick(..))")
//    public void onClickEntryPoint() {
//    }
//
//    @Before("onClickEntryPoint()")
//    public void onClickBefore(JoinPoint joinPoint) {
//        Log.d(TAG, "Before Advice ==> Clicked on : " + ((Button)joinPoint.getArgs()[0]).getText());
//    }
//
//    @Around("onClickEntryPoint()")
//    public void onClickAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.d(TAG, "Around Advice ==> Clicked on : " + ((Button)joinPoint.getArgs()[0]).getText());
//
//        joinPoint.proceed();
//    }
//
//    @After("onClickEntryPoint()")
//    public void onClickAfter(JoinPoint joinPoint) {
//        Log.d(TAG, "After Advice ==> Clicked on : " + ((Button)joinPoint.getArgs()[0]).getText());
//    }
//
//    @AfterReturning(pointcut = "onClickEntryPoint()")
//    public void onClickAfterReturning() {
//        Log.d(TAG, "AfterReturning Advice ==>");
//    }


}
