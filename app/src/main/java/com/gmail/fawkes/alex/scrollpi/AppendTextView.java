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

import android.widget.TextView;

class AppendTextView implements Runnable {
    private static final int LINE_COUNT_LIMIT = 2000;
    private final TextView view;
    private final String text;

    public AppendTextView(final TextView view, final String text) {
        this.view = view;
        this.text = text;
    }

    @Override
    public void run() {
        view.append(text);

        if (isTooLong()) {
            truncateFront();
        }
    }

    private void truncateFront() {
        view.setText(getTrailingLines());
    }

    private boolean isTooLong() {
        return countLines() > LINE_COUNT_LIMIT;
    }

    private int countLines() {
        return view.getText().length();
    }

    private CharSequence getTrailingLines() {
        return view.getText().subSequence(getTruncatedStartIndex(), countLines());
    }

    private int getTruncatedLineCount() {
        return LINE_COUNT_LIMIT * 3 / 4;
    }

    private int getTruncatedStartIndex() {
        return countLines() - getTruncatedLineCount();
    }
}
