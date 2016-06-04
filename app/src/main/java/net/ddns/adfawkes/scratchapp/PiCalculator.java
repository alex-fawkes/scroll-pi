package net.ddns.adfawkes.scratchapp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Calculates pi to an arbitrary number of digits using Bellard's formula:
 * <p/>
 * <pre>
 * scale = 1 / 2^6
 * left(n) = (-1)^n / 2^(10n)
 *
 * right0(n) = -2^5 / ( 4n + 1)
 * right1(n) = -1   / ( 4n + 3)
 * right2(n) =  2^8 / (10n + 1)
 * right3(n) = -2^6 / (10n + 3)
 * right4(n) = -2^2 / (10n + 5)
 * right5(n) = -2^2 / (10n + 7)
 * right6(n) =  1   / (10n + 9)
 *
 * right(n) = right0(n) + right1(n) + ... + right6(n)
 *
 * addend(n) = left(n) + right(n)
 *
 * bellard(n) = scale * sigma(0, n, addend(n))
 * </pre>
 */
public class PiCalculator {
    private MathContext context = MathContext.DECIMAL128;

    public BigDecimal calculateTo(int digits) {
        adjustPrecision(digits);

        BigDecimal pi = big(0);
        for (int i = 0; i < digits; ++i) {
            pi = pi.add(calculateAddend(i));
        }
        return round(pi.multiply(calculateScale()), digits);
    }

    public BigDecimal calculateScale() {
        return big(1).divide(big(2).pow(6), context);
    }

    public BigDecimal calculateLeft(int n) {
        return big(1).negate().pow(n).divide(big(2).pow(10).pow(n), context);
    }

    public BigDecimal calculateRight(BigDecimal n) {
        return calculateRight6(n).add(
                calculateRight5(n).add(calculateRight4(n).add(
                        calculateRight3(n).add(calculateRight2(n).add(
                                calculateRight1(n).add(calculateRight0(n)))))));
    }

    public BigDecimal calculateRight0(BigDecimal n) {
        return big(2).pow(5).divide(big(4).multiply(n).add(big(1)), context).negate();
    }

    public BigDecimal calculateRight1(BigDecimal n) {
        return big(1).divide(big(4).multiply(n).add(big(3)), context).negate();
    }

    public BigDecimal calculateRight2(BigDecimal n) {
        return big(2).pow(8).divide(big(10).multiply(n).add(big(1)), context);
    }

    public BigDecimal calculateRight3(BigDecimal n) {
        return big(2).pow(6).divide(big(10).multiply(n).add(big(3)), context).negate();
    }

    public BigDecimal calculateRight4(BigDecimal n) {
        return big(2).pow(2).divide(big(10).multiply(n).add(big(5)), context).negate();
    }

    public BigDecimal calculateRight5(BigDecimal n) {
        return big(2).pow(2).divide(big(10).multiply(n).add(big(7)), context).negate();
    }

    public BigDecimal calculateRight6(BigDecimal n) {
        return big(1).divide(big(10).multiply(n).add(big(9)), context);
    }

    public BigDecimal calculateAddend(int n) {
        return calculateLeft(n).multiply(calculateRight(big(n)));
    }

    private BigDecimal big(int n) {
        return new BigDecimal(n);
    }

    private BigDecimal round(BigDecimal n, int digits) {
        if (digits < 1) return n;
        return n.setScale(digits - 1, RoundingMode.DOWN);
    }

    private void adjustPrecision(int digits) {
        int precision = context.getPrecision();
        if (digits > precision) context = new MathContext(digits, RoundingMode.HALF_EVEN);
    }
}
