package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;


/**
 * Calculates pi to an arbitrary number of digits using the
 * Bailey–Borwein–Plouffe formula:
 * <p/>
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
 * <p/>
 * This is not the spigot form of the algorithm, which can calculate
 * any digit of pi without calculating preceding digits.
 */
// TODO: deduplicate versus bellard, native bpp_calculation
// TODO: general cleanup
public class BbpCalculator extends BaseCalculator {
    @Override
    protected BigDecimal calculateAddend(final BigDecimal n) {
        return left(n).multiply(right(n));
    }

    @Override
    protected BigDecimal scaleSum(final BigDecimal n) {
        return n;
    }

    private BigDecimal left(final BigDecimal n) {
        return invert(big(16).pow(n.intValue()));
    }

    private BigDecimal right(final BigDecimal n) {
        return right3(n).add(right2(n).add(right1(n).add(right0(n))));
    }

    private BigDecimal right0(final BigDecimal n) {
        return big(4).multiply(invert(big(8).multiply(n).add(big(1))));
    }

    private BigDecimal right1(final BigDecimal n) {
        return big(2).multiply(invert(big(8).multiply(n).add(big(4)))).negate();
    }

    private BigDecimal right2(final BigDecimal n) {
        return invert(big(8).multiply(n).add(big(5))).negate();
    }

    private BigDecimal right3(final BigDecimal n) {
        return invert(big(8).multiply(n).add(big(6))).negate();
    }
}
