package net.ddns.adfawkes.scratchapp;

public class BbpCalculatorTest extends PiCalculatorTest {
    @Override
    public IPiCalculator getCalculator() {
        return new BbpCalculator();
    }
}
