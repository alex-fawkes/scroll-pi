package com.gmail.fawkes.alex.scrollpi.math;

public class BellardCalculatorTest extends PiCalculatorTest {
    @Override
    public IPiCalculator getCalculator() {
        return new BellardCalculator();
    }
}
