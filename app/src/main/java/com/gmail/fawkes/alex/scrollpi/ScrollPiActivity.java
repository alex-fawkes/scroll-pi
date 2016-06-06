package com.gmail.fawkes.alex.scrollpi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gmail.fawkes.alex.scrollpi.utilities.U;

import java.util.Random;

public class ScrollPiActivity extends Activity {
    private static final Random random = new Random();

    @Override
    protected void onCreate(final Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scroll_pi);
        new Thread(new Loop()).start();
    }

    private void displayRandom() {
        View view = findViewById(R.id.textView);
        if (view instanceof TextView) {
            final StringBuilder text = new StringBuilder();
            for (int i = 0; i < 100; ++i) {
                text.append(U.str(random.nextInt(10)));
            }
            view.post(new Append((TextView) view, text.toString()));
        }
    }

    private class Loop implements Runnable {
        @Override
        public void run() {
            try {
                loop();
            } catch (final InterruptedException e) {
                // stop
            }
        }

        @SuppressWarnings("InfiniteLoopStatement") // threaded infinite pi calculation
        private void loop() throws InterruptedException {
            while (true) {
                displayRandom();
                Thread.sleep(10);
            }
        }

    }

    private class Append implements Runnable {
        private final TextView view;
        private final String text;

        public Append(TextView view, String text) {
            this.view = view;
            this.text = text;
        }

        @Override
        public void run() {
            view.append(text);

            // TODO: extract fn
            final CharSequence current = view.getText();
            final int length = current.length();

            final int limit = 2000;
            if (length > limit) {
                final int begin = length - limit * 3 / 4;
                view.setText(current.subSequence(begin, length));
            }
        }
    }
}
