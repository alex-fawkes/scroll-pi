package net.ddns.adfawkes.scratchapp;

public class BbpCalculatorTest extends IPiCalculatorTest {
    @Override
    public IPiCalculator getCalculator() {
        return new BbpCalculator();
    }
}
