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

package com.gmail.fawkes.alex.scrollpi.math.testing;

import java.util.List;

import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.join;
import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.map;
import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.quote;

/**
 * Thrown when PiCalculatorRunner encounters failed test cases.
 */
class TestFailedException extends Exception {
    public TestFailedException(final List<CaseFailedException> cases) {
        super(getJsonCaseList(map(cases, new FailedCaseToJson())));
    }

    private static String getJsonCaseList(final List<String> elements) {
        // java json api will not run on android unless we provide
        // the jar; just do without since the use case is simple
        return quote("{ 'cases': [ ") + join(elements, ", ") + " ] }";
    }
}
