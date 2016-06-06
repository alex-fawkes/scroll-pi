package com.gmail.fawkes.alex.scrollpi.math;

final class NativeBbp {
    static {
        System.loadLibrary("fawkes");
    }

    private NativeBbp() {
    }

    public static native String calculateTo(int digits);

    public static native String calculateFrom(int n, int digits);

    public static native String calculateDigitsFrom(int n, int digits);
}
