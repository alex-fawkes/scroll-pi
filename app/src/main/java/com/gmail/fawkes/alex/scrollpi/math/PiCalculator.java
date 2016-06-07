package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;

public interface PiCalculator {
    BigDecimal calculate(int digits);

    BigDecimal calculateFrom(int index, int digits);

    BigDecimal calculateDigitsFrom(int index, int digits);
}
