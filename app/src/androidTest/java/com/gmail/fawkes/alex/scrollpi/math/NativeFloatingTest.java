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

import junit.framework.Assert;

public class NativeFloatingTest extends ApplicationTestCase<Application> {
    private static final double TEST_VALUE = 1234.5678;
    private static final double DELTA = 0.00000001;

    public NativeFloatingTest() {
        super(Application.class);
    }

    @MediumTest
    public void testTruncate_isCorrect3() throws Exception {
        assertNear(1234.5670, NativeFloating.truncate(TEST_VALUE, 3));
    }

    @MediumTest
    public void testTruncate_isCorrect0() throws Exception {
        assertNear(1234.0, NativeFloating.truncate(TEST_VALUE, 0));
    }

    @MediumTest
    public void testTruncate_isCorrectN3() throws Exception {
        assertNear(1000.0, NativeFloating.truncate(TEST_VALUE, -3));
    }

    @MediumTest
    public void testTruncateFront_isCorrect3() throws Exception {
        assertNear(234.5678, NativeFloating.truncateFront(TEST_VALUE, 3));
    }

    @MediumTest
    public void testTruncateFront_isCorrect0() throws Exception {
        assertNear(0.5678, NativeFloating.truncateFront(TEST_VALUE, 0));
    }

    @MediumTest
    public void testTruncateFront_isCorrectN3() throws Exception {
        assertNear(0.0008, NativeFloating.truncateFront(TEST_VALUE, -3));
    }

    @MediumTest
    public void testTruncateBoth_isCorrect0And0() throws Exception {
        assertNear(0.0, NativeFloating.truncateBoth(TEST_VALUE, 0, 0));
    }

    @MediumTest
    public void testTruncateBoth_isCorrect2And2() throws Exception {
        assertNear(34.56, NativeFloating.truncateBoth(TEST_VALUE, 2, 2));
    }

    @MediumTest
    public void testTruncateBoth_isCorrectN1And3() throws Exception {
        assertNear(0.067, NativeFloating.truncateBoth(TEST_VALUE, -1, 3));
    }

    @MediumTest
    public void testDecimalShiftLeft_isCorrect3() throws Exception {
        assertNear(1234567.8, NativeFloating.decimalShiftLeft(TEST_VALUE, 3));
    }

    @MediumTest
    public void testDecimalShiftLeft_isCorrect0() throws Exception {
        assertNear(1234.5678, NativeFloating.decimalShiftLeft(TEST_VALUE, 0));
    }

    @MediumTest
    public void testDecimalShiftLeft_isCorrectN3() throws Exception {
        assertNear(1.2345678, NativeFloating.decimalShiftLeft(TEST_VALUE, -3));
    }

    @MediumTest
    public void testDecimalShiftRight_isCorrect3() throws Exception {
        assertNear(1.2345678, NativeFloating.decimalShiftRight(TEST_VALUE, 3));
    }

    @MediumTest
    public void testDecimalShiftRight_isCorrect0() throws Exception {
        assertNear(1234.5678, NativeFloating.decimalShiftRight(TEST_VALUE, 0));
    }

    @MediumTest
    public void testDecimalShiftRight_isCorrectN3() throws Exception {
        assertNear(1234567.8, NativeFloating.decimalShiftRight(TEST_VALUE, -3));
    }

    private void assertNear(double expected, double actual) {
        Assert.assertEquals(expected, actual, DELTA);
    }
}
