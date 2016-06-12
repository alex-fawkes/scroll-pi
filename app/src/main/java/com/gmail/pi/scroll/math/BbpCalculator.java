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

package com.gmail.pi.scroll.math;

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
