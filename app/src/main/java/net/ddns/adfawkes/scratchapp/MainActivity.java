package net.ddns.adfawkes.scratchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.textView);
        if (view instanceof TextView) {
            TextView text = (TextView) view;
            text.setMovementMethod(new ScrollingMovementMethod());
            text.setText(stringFromJNI2());
        }
    }

    public native String stringFromJNI2();

    static {
        System.loadLibrary("hello");
    }
}
