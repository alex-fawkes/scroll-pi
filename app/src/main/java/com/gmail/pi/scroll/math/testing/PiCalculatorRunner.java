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

package com.gmail.pi.scroll.math.testing;

import com.gmail.pi.scroll.math.PiCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Custom test runner for PiCalculator tests. Since all the cases get
 * combined into a single framework case, this collects exception messages
 * to identify all failed tests.
 */
public class PiCalculatorRunner {
    // TODO: remove and simplify to unit tests now that native does not need to share logic
    private final PiCalculator calculator;

    public PiCalculatorRunner(final PiCalculator calculator) {
        this.calculator = calculator;
    }

    public void run(final Collection<PiCalculatorCase> cases) throws TestFailedException {
        final List<CaseFailedException> exceptions = new ArrayList<>();
        for (final PiCalculatorCase c : cases) {
            try {
                c.test(calculator);
            } catch (final CaseFailedException exception) {
                exceptions.add(exception);
            }
        }

        if (!exceptions.isEmpty()) {
            throw new TestFailedException(exceptions);
        }
    }
}
