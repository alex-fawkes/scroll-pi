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

package com.gmail.pi.scroll;

import android.os.Handler;

import com.gmail.pi.scroll.math.NativeBbp;

class CalculatePiLoop implements Runnable {
    private final Handler handler;

    private final StringBuilder buffer = new StringBuilder();
    private long lastUpdateTimeMillis;
    private long digitsCalculated;

    CalculatePiLoop(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        waitForGuiThread(); // the first few digits do not display otherwise
        loop();
    }

    @SuppressWarnings("InfiniteLoopStatement") // threaded infinite pi calculation
    private void loop() {
        while (true) {
            sendCalculatedDigits();
        }
    }

    private void waitForGuiThread() {
        try {
            Thread.sleep(500);
        } catch (final InterruptedException e) {
            // continue
        }
    }

    private void sendCalculatedDigits() {
        buffer.append(calculateNextDigits());
        if (isUpdateTime()) {
            lastUpdateTimeMillis = System.currentTimeMillis();
            sendNewDigitsMessage(flushDigitsBuffer());
        }
    }

    private void sendNewDigitsMessage(String digits) {
        new NewDigitsEvent(digits, digitsCalculated).sendMessage(handler);
    }

    private String calculateNextDigits() {
        final long index = digitsCalculated;
        final long digits = 8;
        final String text = NativeBbp.calculateHexDigitsFrom(index, digits);
        digitsCalculated += digits;

        if (index == 0) {
            return "0x" + text;
        }
        return text;
    }

    private boolean isUpdateTime() {
        final long updateIntervalMillis = 250;
        final long elapsed = System.currentTimeMillis() - lastUpdateTimeMillis;
        return elapsed >= updateIntervalMillis;
    }

    private String flushDigitsBuffer() {
        final String content = buffer.toString();
        buffer.setLength(0);
        return content;
    }
}
