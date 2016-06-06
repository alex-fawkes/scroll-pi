package com.gmail.fawkes.alex.scrollpi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.string;

public class ScrollPiActivity extends Activity {
    private static final Random random = new Random();

    @Override
    protected void onCreate(final Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scroll_pi);
        new Thread(new Loop()).start();
    }

    private void displayRandomDigits() {
        View view = findViewById(R.id.textView);
        if (view instanceof TextView) {
            final String text = getRandomDigits();
            view.post(new AppendTextView((TextView) view, text));
        }
    }

    private String getRandomDigits() {
        final StringBuilder text = new StringBuilder();
        for (int i = 0; i < 100; ++i) {
            final int digit = random.nextInt(10);
            text.append(string(digit));
        }
        return text.toString();
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
                displayRandomDigits();
                Thread.sleep(10);
            }
        }
    }
}
