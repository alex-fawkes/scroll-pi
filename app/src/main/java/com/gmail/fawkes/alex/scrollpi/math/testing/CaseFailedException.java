package com.gmail.fawkes.alex.scrollpi.math.testing;

import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.quote;

class CaseFailedException extends Exception {
    public CaseFailedException(
            final String description, final String expected, final String actual) {
        super(toJson(description, expected, actual));
    }

    private static String toJson(
            final String description, final String expected, final String actual) {
        final String format = quote("{ 'description': '%s', 'expected': %s, 'actual': %s }");
        return String.format(format, description, expected, actual);
    }

    public String toJson() {
        return getMessage();
    }
}
