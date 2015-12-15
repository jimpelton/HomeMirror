package com.morristaedt.mirror.aspect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jim on 12/12/15.
 */
public class GlobalData implements Iterable {


    private static final List<Result> resultsList =
            Collections.synchronizedList(new ArrayList<Result>());

    public void add(boolean passed, String message) {
        Result r = new Result();
        r.passed = passed;
        r.message = message;
        resultsList.add(r);
    }

    public Iterator<Result> iterator() {
        return resultsList.iterator();
    }
}
