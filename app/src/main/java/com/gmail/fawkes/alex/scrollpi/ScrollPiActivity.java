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

package com.gmail.fawkes.alex.scrollpi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gmail.fawkes.alex.scrollpi.math.NativeBbp;

public class ScrollPiActivity extends Activity {
    private int digitsDisplayed;

    @Override
    protected void onCreate(final Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scroll_pi);
        new Thread(new Loop()).start();
    }

    private void displayPiDigits() {
        View view = findViewById(R.id.textView);
        if (view instanceof TextView) {
            final String text = calculateNextPiDigits();
            view.post(new AppendTextView((TextView) view, text));
        }
    }

    private String calculateNextPiDigits() {
        final int index = digitsDisplayed;
        final int digits = 8;
        digitsDisplayed += digits;

        final String text = NativeBbp.calculateHexDigitsFrom(index, digits);
        if (index == 0) {
            return "0x" + text;
        }
        return text;
    }

    private class Loop implements Runnable {
        @Override
        public void run() {
            try {
                waitForGuiLoad();
            } catch (final InterruptedException exception) {
                // continue
            }
            loop();
        }

        @SuppressWarnings("InfiniteLoopStatement") // threaded infinite pi calculation
        private void loop() {
            while (true) {
                displayPiDigits();
            }
        }

        private void waitForGuiLoad() throws InterruptedException {
            Thread.sleep(500);
        }
    }
}
