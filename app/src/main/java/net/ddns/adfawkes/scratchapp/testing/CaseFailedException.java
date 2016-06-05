package net.ddns.adfawkes.scratchapp.testing;

import net.ddns.adfawkes.scratchapp.extensions.X;

public class CaseFailedException extends Exception {
    public CaseFailedException(String description, String expected, String actual) {
        super(toJson(description, expected, actual));
    }

    public String toJson() {
        return getMessage();
    }

    private static String toJson(String description, String expected, String actual) {
        final String format = X.quote("{ 'description': '%s', 'expected': %s, 'actual': %s }");
        return String.format(format, description, expected, actual);
    }
}
