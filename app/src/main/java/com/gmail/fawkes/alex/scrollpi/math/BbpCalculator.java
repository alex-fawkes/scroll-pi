package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;


/**
 * Calculates pi to an arbitrary number of digits using the
 * Bailey–Borwein–Plouffe formula:
 * <p>
 * <pre>
 * pi = sigma(n = 0, inf, addend(n))
 *
 * addend(n) = left(n) + right(n)
 *
 * left(n) = 1 / 16^n
 *
 * right(n) = right0(n) + right1(n) + right2(n) + right3(n)
 *
 * right0(n) =  4 / (8n + 1)
 * right1(n) = -2 / (8n + 4)
 * right2(n) = -1 / (8n + 5)
 * right3(n) = -1 / (8n + 6)
 * </pre>
 * <p>
 * This is not the spigot form of the algorithm, which can calculate
 * any digit of pi without calculating preceding digits.
 */
public class BbpCalculator extends BaseCalculator {
    @Override
    protected BigDecimal calculateAddend(final BigDecimal index) {
        return left(index).multiply(right(index));
    }

    @Override
    protected BigDecimal scaleSum(final BigDecimal index) {
        return index;
    }

    private BigDecimal left(final BigDecimal index) {
        return invert(big(16).pow(index.intValue()));
    }

    private BigDecimal right(final BigDecimal index) {
        return right3(index).add(right2(index).add(right1(index).add(right0(index))));
    }

    private BigDecimal right0(final BigDecimal index) {
        return big(4).multiply(invert(big(8).multiply(index).add(big(1))));
    }

    private BigDecimal right1(final BigDecimal index) {
        return big(2).multiply(invert(big(8).multiply(index).add(big(4)))).negate();
    }

    private BigDecimal right2(final BigDecimal index) {
        return invert(big(8).multiply(index).add(big(5))).negate();
    }

    private BigDecimal right3(final BigDecimal index) {
        return invert(big(8).multiply(index).add(big(6))).negate();
    }
}
