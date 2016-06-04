package net.ddns.adfawkes.scratchapp;

final class NativeBppCalc {
    private NativeBppCalc() {}

    public static native String calculateTo(int digits);
    public static native String calculateFrom(int n, int digits);
    public static native String calculateDigitsFrom(int n, int digits);

    static {
        System.loadLibrary("picalc");
    }
}
