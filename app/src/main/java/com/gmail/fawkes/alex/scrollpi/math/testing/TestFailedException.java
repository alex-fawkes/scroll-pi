package com.gmail.fawkes.alex.scrollpi.math.testing;

import java.util.List;

import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.join;
import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.map;
import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.quote;

/**
 * Thrown when PiCalculatorRunner encounters failed test cases.
 */
class TestFailedException extends Exception {
    public TestFailedException(final List<CaseFailedException> cases) {
        super(getJsonCaseList(map(cases, new FailedCaseToJson())));
    }

    private static String getJsonCaseList(final List<String> elements) {
        // java json api will not run on android unless we provide
        // the jar; just do without since the use case is simple
        return quote("{ 'cases': [ ") + join(elements, ", ") + " ] }";
    }
}
