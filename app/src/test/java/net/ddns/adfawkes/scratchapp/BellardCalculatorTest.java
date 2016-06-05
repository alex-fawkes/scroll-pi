package net.ddns.adfawkes.scratchapp;

public class BellardCalculatorTest extends IPiCalculatorTest {
    @Override
    public IPiCalculator getCalculator() {
        return new BellardCalculator();
    }
}
