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
