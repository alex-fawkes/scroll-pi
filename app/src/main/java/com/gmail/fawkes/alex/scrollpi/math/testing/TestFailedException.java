package com.gmail.fawkes.alex.scrollpi.math.testing;

import java.util.ArrayList;
import java.util.List;


import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.first;
import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.quote;

/**
 * Thrown when PiCalculatorRunner encounters failed test cases.
 */
class TestFailedException extends Exception {
    public TestFailedException(final List<CaseFailedException> cases) {
        super(getJsonList(getJsonElements(cases)));
    }

    private static List<String> getJsonElements(final List<CaseFailedException> cases) {
        final List<String> elements = new ArrayList<>();
        for (final CaseFailedException c : cases) {
            elements.add(c.toJson());
        }
        return elements;
    }

    private static String getJsonList(final List<String> elements) {
        // java json api will not run on android unless we provide
        // the jar; just do without since the use case is simple
        final StringBuilder list = new StringBuilder();
        list.append(quote("{ 'cases': [ "));
        for (final String e : elements) {
            if (!first(elements, e)) {
                list.append(", ");
            }
            list.append(e);
        }
        list.append(" ] }");
        return list.toString();
    }
}
