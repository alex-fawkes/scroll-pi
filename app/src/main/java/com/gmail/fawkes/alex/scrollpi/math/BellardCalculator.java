package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;

/**
 * Calculates pi to an arbitrary number of digits using Bellard's formula:
 * <p>
 * <pre>
 * pi = scale * sigma(n = 0, inf, addend(n))
 *
 * scale = 1 / 2^6
 * addend(n) = left(n) + right(n)
 *
 * left(n) = (-1)^n / 2^(10n)
 *
 * right(n) = right0(n) + right1(n) + ... + right6(n)
 *
 * right0(n) = -2^5 / ( 4n + 1)
 * right1(n) = -1   / ( 4n + 3)
 * right2(n) =  2^8 / (10n + 1)
 * right3(n) = -2^6 / (10n + 3)
 * right4(n) = -2^2 / (10n + 5)
 * right5(n) = -2^2 / (10n + 7)
 * right6(n) =  1   / (10n + 9)
 * </pre>
 * <p>
 * Wikipedia has this as 40% faster than Bailey–Borwein–Plouffe, but unlike
 * BBP it is not able to calculate digits independently of previous digits.
 */
public class BellardCalculator extends BaseCalculator {
    @Override
    protected BigDecimal calculateAddend(final BigDecimal index) {
        return left(index).multiply(right(index));
    }

    @Override
    protected BigDecimal scaleSum(final BigDecimal index) {
        return index.multiply(scale());
    }

    private BigDecimal left(final BigDecimal index) {
        return big(1).negate().pow(index.intValue()).multiply(invert(divisor(index)));
    }

    private BigDecimal divisor(final BigDecimal index) {
        return big(2).pow(10).pow(index.intValue());
    }

    private BigDecimal right(final BigDecimal index) {
        return right6(index).add(right5(index).add(right4(index).add(
                right3(index).add(right2(index).add(right1(index).add(right0(index)))))));
    }

    private BigDecimal right0(final BigDecimal index) {
        return big(2).pow(5).multiply(invert(big(4).multiply(index).add(big(1)))).negate();
    }

    private BigDecimal right1(final BigDecimal index) {
        return big(1).multiply(invert(big(4).multiply(index).add(big(3)))).negate();
    }

    private BigDecimal right2(final BigDecimal index) {
        return big(2).pow(8).multiply(invert(big(10).multiply(index).add(big(1))));
    }

    private BigDecimal right3(final BigDecimal index) {
        return big(2).pow(6).multiply(invert(big(10).multiply(index).add(big(3)))).negate();
    }

    private BigDecimal right4(final BigDecimal index) {
        return big(2).pow(2).multiply(invert(big(10).multiply(index).add(big(5)))).negate();
    }

    private BigDecimal right5(final BigDecimal index) {
        return big(2).pow(2).multiply(invert(big(10).multiply(index).add(big(7)))).negate();
    }

    private BigDecimal right6(final BigDecimal index) {
        return big(1).multiply(invert(big(10).multiply(index).add(big(9))));
    }

    private BigDecimal scale() {
        return big(1).multiply(invert(big(2).pow(6)));
    }
}
