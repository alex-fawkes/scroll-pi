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
import android.os.Message;

class NewDigitsEvent {
    private static final int NEW_DIGITS_MESSAGE = 0;

    final String digits;
    final long digitsCalculated;

    NewDigitsEvent(String digits, long digitsCalculated) {
        this.digits = digits;
        this.digitsCalculated = digitsCalculated;
    }

    static NewDigitsEvent tryParse(Message message) {
        if (message.what == NEW_DIGITS_MESSAGE) {
            return (NewDigitsEvent) message.obj;
        }
        return null;
    }

    void sendMessage(Handler handler) {
        handler.sendMessage(obtainMessage(handler));
    }

    private Message obtainMessage(Handler handler) {
        return Message.obtain(handler, NEW_DIGITS_MESSAGE, this);
    }
}
