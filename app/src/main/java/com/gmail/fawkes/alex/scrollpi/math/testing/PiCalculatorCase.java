package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.PiCalculator;

/**
 * Test case for PiCalculator implementations.
 */
interface PiCalculatorCase {
    void test(PiCalculator calculator) throws CaseFailedException;
}
