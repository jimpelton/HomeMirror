package com.morristaedt.mirror.aspect;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jim on 12/12/15.
 */
public class GlobalData {
    // configurations
    public static boolean isCelsius = false;
    public static boolean showNextCalendarEventChecked = false;
    public static boolean showNewsHeadlineChecked = false;
    public static boolean showXKCDChecked = false;
    public static boolean stockSymbolSet = false;
    public static boolean showBikingHintChecked = false;
    public static boolean haveLocation = false;

    public static class Test {
        public Result result;
    }

    private static final List<Result> resultsList =
            Collections.synchronizedList(new ArrayList<Result>());


    public static void addResult(boolean passed, String message) {
        Result r = new Result();
        r.passed = passed;
        r.message = message;
        resultsList.add(r);
    }

}
