package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;

public interface PiCalculator {
    BigDecimal calculate(int digits);

    BigDecimal calculateFrom(int n, int digits);

    BigDecimal calculateDigitsFrom(int n, int digits);
}
