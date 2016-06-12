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

public class NativeBbpBaileyTest extends ApplicationTestCase<Application> {
    public NativeBbpBaileyTest() {
        super(Application.class);
    }

    @MediumTest
    public void test8From0() throws Exception {
        test8From("3.243f6a8", 0);
    }

    @MediumTest
    public void test8From1() throws Exception {
        test8From("243f6a88", 1);
    }

    @MediumTest
    public void test8From4() throws Exception {
        test8From("f6a8885a", 4);
    }

    @MediumTest
    public void test8From8() throws Exception {
        test8From("885a308d", 8);
    }

    @MediumTest
    public void test8BackFrom100() throws Exception {
        test8BackFrom("90c6cc0a", 100);
    }

    @MediumTest
    public void test36BackFrom50() throws Exception {
        testBackFrom("8d313198a2e03707344a4093822299f31d00", 50, 36);
    }

    @MediumTest
    public void test8BackFrom1000() throws Exception {
        test8BackFrom("8db0fead", 1000);
    }

    @MediumTest
    public void test8BackFrom2000() throws Exception {
        test8BackFrom("202e5b9c", 2000);
    }

    @MediumTest
    public void test8BackFrom3000() throws Exception {
        test8BackFrom("b043556f", 3000);
    }

    @MediumTest
    public void test8BackFrom4000() throws Exception {
        test8BackFrom("28d937e4", 4000);
    }

    @MediumTest
    public void test8BackFrom5000() throws Exception {
        test8BackFrom("eaf664fd", 5000);
    }

    @MediumTest
    public void test8BackFrom10000() throws Exception {
        test8BackFrom("6aab49ec", 10000);
    }

    @MediumTest
    public void test36BackFrom10000() throws Exception {
        testBackFrom("3ee0b5e57368f6c79f4bb7a595926aab49ec", 10000, 36);
    }

    @MediumTest
    public void test8BackFrom100000() throws Exception {
        test8BackFrom("22673c1a", 100000);
    }

    @MediumTest
    public void test36BackFrom100000() throws Exception {
        testBackFrom("58b3e62e612bc302ec487aa9a6ea22673c1a", 100000, 36);
    }

    @MediumTest
    public void test8BackFrom1000000() throws Exception {
        test8BackFrom("ffd34236", 1000000);
    }

    private void test(String expected, long index, long digits) {
        assertEquals(expected, NativeBbpBailey.calculateHexDigitsFrom(index, digits));
    }

    private void testFrom(String expected, long index, long digits) {
        test(expected, index, digits);
    }

    private void testBackFrom(String expected, long index, long digits) {
        test(expected, index - digits, digits);
    }

    private void test8From(String expected, long index) {
        testFrom(expected, index, 8);
    }

    private void test8BackFrom(String expected, long index) {
        testBackFrom(expected, index, 8);
    }
}
