package net.ddns.adfawkes.scratchapp;

import java.math.BigDecimal;

interface IPiCalculator {
    BigDecimal calculateTo(int digits);

    BigDecimal calculateFrom(int n, int digits);

    BigDecimal calculateDigitsFrom(int n, int digits);
}
