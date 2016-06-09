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

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import com.gmail.fawkes.alex.scrollpi.math.testing.PiCalculatorCaseSet;
import com.gmail.fawkes.alex.scrollpi.math.testing.PiCalculatorRunner;

/**
 * This is an application test rather than unit test because we need native library access.
 */
public class FastBbpCalculatorTest extends ApplicationTestCase<Application> {
    private static final PiCalculatorRunner runner = getRunner();

    public FastBbpCalculatorTest() {
        super(Application.class);
    }

    private static PiCalculatorRunner getRunner() {
        return new PiCalculatorRunner(new FastBbpCalculator());
    }

    @MediumTest
    public void testAllCases() throws Exception {
        runner.run(PiCalculatorCaseSet.getAllCases());
    }

    @MediumTest
    public void testTenThousandthDigits() throws Exception {
        // 95968 81592 05600 10165 52563 7567 (10001th)
        assertEquals("95968815920560010165525637567", NativeBbpAlgorithm.calculateDigitsFrom(10000 - 25 - 4 + 1, 25 + 4));
    }


}
