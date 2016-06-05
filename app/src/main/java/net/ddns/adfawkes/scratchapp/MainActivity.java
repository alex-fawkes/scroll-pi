package net.ddns.adfawkes.scratchapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.ddns.adfawkes.scratchapp.extensions.X;

import java.util.Random;

public class MainActivity extends Activity {
    static {
        System.loadLibrary("picalc");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Loop()).start();
    }

    private void displayRandom() {
        View view = findViewById(R.id.textView);
        if (view instanceof TextView) {
            // avoid threading issues entirely with local random instance
            Random random = new Random();

            StringBuilder text = new StringBuilder();
            for (int i = 0; i < 100; ++i) {
                text.append(X.str(random.nextInt(10)));
            }
            view.post(new Append((TextView) view, text.toString()));
        }
    }

    private class Loop implements Runnable {
        @Override
        public void run() {
            try {
                loop();
            } catch (InterruptedException e) {
                // stop
            }
        }

        @SuppressWarnings("InfiniteLoopStatement")
        private void loop() throws InterruptedException {
            while (true) {
                displayRandom();
                Thread.sleep(10);
            }
        }

    }

    // TODO: final locals, members
    private class Append implements Runnable {
        private final TextView view;
        private final String text;

        public Append(TextView view, String text) {
            this.view = view;
            this.text = text;
        }

        @Override
        public void run() {
            // TODO: rename class
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
