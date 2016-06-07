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

/**
 * BBP calculator over JNI bindings.
 */
public class FastBbpCalculator implements PiCalculator {
    public BigDecimal calculate(final int digits) {
        return new BigDecimal(NativeBbpAlgorithm.calculate(digits));
    }

    public BigDecimal calculateFrom(final int index, final int digits) {
        return new BigDecimal(NativeBbpAlgorithm.calculateFrom(index, digits));
    }

    public BigDecimal calculateDigitsFrom(final int index, final int digits) {
        return new BigDecimal(NativeBbpAlgorithm.calculateDigitsFrom(index, digits));
    }
}
