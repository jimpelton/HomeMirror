package com.morristaedt.mirror.aspect;

/**
 * Created by jim on 12/12/15.
 */
public class Result {
        public boolean passed;
        public String message;
        public String hash;

        @Override
        public boolean equals(Object o) {
            if (o==this) {
                return true;
            }
            if (! (o instanceof Result)) {
                return false;
            }

            Result r = (Result) o;
            return passed == r.passed &&
                    message.equals(r.message) &&
                    hash.equals(r.hash);
        }
}
