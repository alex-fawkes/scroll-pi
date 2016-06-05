package net.ddns.adfawkes.scratchapp;

public class BellardCalculatorTest extends PiCalculatorTest {
    @Override
    public IPiCalculator getCalculator() {
        return new BellardCalculator();
    }
}
