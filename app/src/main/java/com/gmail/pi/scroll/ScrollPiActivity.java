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

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class ScrollPiActivity extends Activity {
    @Override
    protected void onCreate(final Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scroll_pi);
        new Thread(new CalculatePiLoop(createHandler())).start();
    }

    private void displayDigits(String digits) {
        final TextView view = (TextView) findViewById(R.id.digitsScrollText);
        if (view == null) {
            return;
        }
        new DigitsTextScroller(view).scroll(digits);
    }

    private void displayDigitsCount(long count) {
        final TextView view = (TextView) findViewById(R.id.digitsCountText);
        if (view == null) {
            return;
        }
        view.setText(String.valueOf(count));
    }

    private Handler createHandler() {
        return new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                final NewDigitsEvent event = NewDigitsEvent.tryParse(message);
                if (event != null) {
                    displayDigits(event.digits);
                    displayDigitsCount(event.digitsCalculated);
                    return true;
                }
                return false;
            }
        });
    }
}
