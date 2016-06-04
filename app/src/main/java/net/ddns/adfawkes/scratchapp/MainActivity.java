package net.ddns.adfawkes.scratchapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private final static int N = 3000;

    private MathContext context = new MathContext(N, RoundingMode.HALF_EVEN);
    
    private BigDecimal big(int n) {
        return new BigDecimal(n);
    }
    
    private BigDecimal bellardAll(BigDecimal n) {
        return bellard7(n).add(bellard6(n).add(bellard5(n).add(bellard4(n).add(bellard3(n).add(bellard2(n).add(bellard1(n)))))));
    }

    private BigDecimal bellard1(BigDecimal n) {
        return big(2).pow(5).divide(big(4).multiply(n).add(big(1)), context).negate();
    }

    private BigDecimal bellard2(BigDecimal n) {
        return big(1).divide(big(4).multiply(n).add(big(3)), context).negate();
    }

    private BigDecimal bellard3(BigDecimal n) {
        return big(2).pow(8).divide(big(10).multiply(n).add(big(1)), context);
    }

    private BigDecimal bellard4(BigDecimal n) {
        return big(2).pow(6).divide(big(10).multiply(n).add(big(3)), context).negate();
    }

    private BigDecimal bellard5(BigDecimal n) {
        return big(2).pow(2).divide(big(10).multiply(n).add(big(5)), context).negate();
    }

    private BigDecimal bellard6(BigDecimal n) {
        return big(2).pow(2).divide(big(10).multiply(n).add(big(7)), context).negate();
    }

    private BigDecimal bellard7(BigDecimal n) {
        return big(1).divide(big(10).multiply(n).add(big(9)), context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = findViewById(R.id.textView);
        if (view instanceof TextView) {
            TextView text = (TextView)view;
            text.setMovementMethod(new ScrollingMovementMethod());

            BigDecimal pi = big(0);
            for (int i = 0; i < N; ++i)
            {
                BigDecimal n = big(i);
                BigDecimal t = big(1).negate().pow(n.intValue()).divide(big(2).pow(10).pow(n.intValue()), context);
                pi = pi.add(t.multiply(bellardAll(n)));
            }
            pi = pi.divide(big(2).pow(6), context);

            text.setText(pi.toString());
        }
    }
}
