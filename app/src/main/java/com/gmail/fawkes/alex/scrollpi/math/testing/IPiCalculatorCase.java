package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.IPiCalculator;

/**
 * Test case for IPiCalculator implementations.
 */
interface IPiCalculatorCase {
    void test(IPiCalculator calculator) throws CaseFailedException;
}
