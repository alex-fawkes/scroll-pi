package net.ddns.adfawkes.scratchapp.testing;

import net.ddns.adfawkes.scratchapp.IPiCalculator;

/**
 * Test case for IPiCalculator implementations.
 */
public interface IPiCalculatorCase {
    void test(IPiCalculator calculator) throws CaseFailedException;
}
