package com.gmail.fawkes.alex.scrollpi.math.testing;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Test cases for PiCalculator implementations. These are defined here for
 * ease of sharing between unit and application tests. It would be ideal to
 * have a separate test function for each case, but it is not worth the risk
 * of missing test cases added to one but not the other.
 */
public final class PiCalculatorCaseSet {
    private PiCalculatorCaseSet() {
    }

    public static Collection<PiCalculatorCase> getAllCases() {
        final ArrayList<PiCalculatorCase> cases = new ArrayList<>();
        cases.addAll(Calculate.getAllCases());
        cases.addAll(CalculateFrom.getAllCases());
        cases.addAll(CalculateDigitsFrom.getAllCases());
        return cases;
    }

    public static final class Calculate {
        private Calculate() {
        }

        public static Collection<PiCalculatorCase> getAllCases() {
            final Collection<PiCalculatorCase> cases = new ArrayList<>();
            cases.add(getIsCorrectTo1());
            cases.add(getIsCorrectTo2());
            cases.add(getIsCorrectTo3());
            cases.add(getIsCorrectTo25());
            cases.add(getIsCorrectTo50());
            return cases;
        }

        private static PiCalculatorCase getIsCorrectTo1() {
            return make("3", 1);
        }

        private static PiCalculatorCase getIsCorrectTo2() {
            return make("3.1", 2);
        }

        private static PiCalculatorCase getIsCorrectTo3() {
            return make("3.14", 3);
        }

        private static PiCalculatorCase getIsCorrectTo25() {
            return make("3.141592653589793238462643", 25);
        }

        private static PiCalculatorCase getIsCorrectTo50() {
            return make("3.1415926535897932384626433832795028841971693993751", 50);
        }

        private static PiCalculatorCase make(String expected, int n) {
            return new CalculateCase(expected, n);
        }
    }

    public static final class CalculateFrom {
        private CalculateFrom() {
        }

        public static Collection<PiCalculatorCase> getAllCases() {
            final Collection<PiCalculatorCase> cases = new ArrayList<>();
            cases.add(getIsCorrect3From0());
            cases.add(getIsCorrect3From1());
            cases.add(getIsCorrect3From2());
            cases.add(getIsCorrect25From0());
            cases.add(getIsCorrect25From25());
            return cases;
        }

        private static PiCalculatorCase getIsCorrect3From0() {
            return make("3.14", 0, 3);
        }

        private static PiCalculatorCase getIsCorrect3From1() {
            return make("0.141", 1, 3);
        }

        private static PiCalculatorCase getIsCorrect3From2() {
            return make("0.0415", 2, 3);
        }

        private static PiCalculatorCase getIsCorrect25From0() {
            return make("3.141592653589793238462643", 0, 25);
        }

        private static PiCalculatorCase getIsCorrect25From25() {
            return make("0.0000000000000000000000003832795028841971693993751", 25, 25);
        }

        private static PiCalculatorCase make(String expected, int n, int digits) {
            return new CalculateFromCase(expected, n, digits);
        }
    }

    public static final class CalculateDigitsFrom {
        private CalculateDigitsFrom() {
        }

        private static Collection<PiCalculatorCase> getAllCases() {
            final Collection<PiCalculatorCase> cases = new ArrayList<>();
            cases.add(getIsCorrect3From0());
            cases.add(getIsCorrect3From1());
            cases.add(getIsCorrect3From2());
            cases.add(getIsCorrect25From0());
            cases.add(getIsCorrect25From25());
            return cases;
        }

        private static PiCalculatorCase getIsCorrect3From0() {
            return make("314", 0, 3);
        }

        private static PiCalculatorCase getIsCorrect3From1() {
            return make("141", 1, 3);
        }

        private static PiCalculatorCase getIsCorrect3From2() {
            return make("415", 2, 3);
        }

        private static PiCalculatorCase getIsCorrect25From0() {
            return make("3141592653589793238462643", 0, 25);
        }

        private static PiCalculatorCase getIsCorrect25From25() {
            return make("3832795028841971693993751", 25, 25);
        }

        private static PiCalculatorCase make(String expected, int n, int digits) {
            return new CalculateDigitsFromCase(expected, n, digits);
        }
    }
}
