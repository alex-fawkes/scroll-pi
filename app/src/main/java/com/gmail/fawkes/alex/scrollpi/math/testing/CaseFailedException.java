package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.utilities.U;

class CaseFailedException extends Exception {
    public CaseFailedException(
            final String description, final String expected, final String actual) {
        super(toJson(description, expected, actual));
    }

    private static String toJson(
            final String description, final String expected, final String actual) {
        final String format = U.quote("{ 'description': '%s', 'expected': %s, 'actual': %s }");
        return String.format(format, description, expected, actual);
    }

    public String toJson() {
        return getMessage();
    }
}
