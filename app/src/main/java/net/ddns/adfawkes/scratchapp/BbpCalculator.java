package net.ddns.adfawkes.scratchapp;

import java.math.BigDecimal;

/**
 * Implementation of Bailey–Borwein–Plouffe formula for calculating pi to
 * an arbitrary number of digits. BBP is a spigot algorithm, meaning it
 * can calculate any digit of pi without calculating preceding digits.
 */
public class BbpCalculator implements IPiCalculator {
    public BigDecimal calculateTo(int digits) {
        return new BigDecimal(NativeBbpCalc.calculateTo(digits));
    }

    public BigDecimal calculateFrom(int n, int digits) {
        return new BigDecimal(NativeBbpCalc.calculateFrom(n, digits));
    }

    public BigDecimal calculateDigitsFrom(int n, int digits) {
        return new BigDecimal(NativeBbpCalc.calculateDigitsFrom(n, digits));
    }
}
