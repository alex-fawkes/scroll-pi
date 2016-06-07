/*
 * Copyright (c) 2016, Alex Fawkes
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public abstract class BaseCalculator implements PiCalculator {
    private MathContext context = MathContext.DECIMAL128;

    public BaseCalculator() {
    }

    @Override
    public final BigDecimal calculate(final int digits) {
        calibrate(digits);
        return pi(digits);
    }

    @Override
    public final BigDecimal calculateFrom(final int index, final int digits) {
        calibrate(digits);
        return mod(calculate(index + digits), mask(index));
    }

    @Override
    public final BigDecimal calculateDigitsFrom(final int index, final int digits) {
        calibrate(digits);
        final BigDecimal scale = big(10).pow(index + digits - 1);
        return calculateFrom(index, digits).multiply(scale).stripTrailingZeros();
    }

    protected abstract BigDecimal calculateAddend(BigDecimal index);

    protected abstract BigDecimal scaleSum(BigDecimal index);

    BigDecimal big(final int index) {
        return new BigDecimal(index);
    }

    BigDecimal invert(final BigDecimal index) {
        return big(1).divide(index, context);
    }

    /**
     * Increase the precision of the math context as more digits are requested.
     * This behavior is "sticky" in that the precision is never decreased.
     */
    private void calibrate(final int digits) {
        final int precision = context.getPrecision();
        if (digits > precision) {
            context = new MathContext(digits, RoundingMode.HALF_EVEN);
        }
    }

    private BigDecimal mod(final BigDecimal index, final BigDecimal divisor) {
        return index.divideAndRemainder(divisor, context)[1];
    }

    private BigDecimal round(final BigDecimal index, final int digits) {
        if (digits < 1) {
            return index;
        }
        return index.setScale(digits - 1, RoundingMode.DOWN);
    }

    private BigDecimal mask(final int digits) {
        if (digits < 1) {
            return big(10);
        }
        return invert(big(10).pow(digits - 1));
    }

    private BigDecimal pi(final int index) {
        BigDecimal sum = big(0);
        for (int i = 0; i < index; ++i) {
            sum = sum.add(calculateAddend(big(i)));
        }
        return round(scaleSum(sum), index);
    }
}
