package net.ddns.adfawkes.scratchapp.testing;

import net.ddns.adfawkes.scratchapp.extensions.X;

import java.util.ArrayList;
import java.util.List;

/**
 * Thrown when PiCalculatorRunner encounters failed test cases.
 */
public class TestFailedException extends Exception {
    public TestFailedException(List<CaseFailedException> cases) {
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
        list.append(X.quote("{ 'cases': [ "));
        for (final String e : elements) {
            if (!X.first(elements, e)) {
                list.append(", ");
            }
            list.append(e);
        }
        list.append(" ] }");
        return list.toString();
    }
}
