package com.gmail.fawkes.alex.scrollpi.math;

class NativeBbpAlgorithm {
    static {
        System.loadLibrary("fawkes");
    }

    private NativeBbpAlgorithm() {
    }

    public static native String calculate(int digits);

    public static native String calculateFrom(int value, int digits);

    public static native String calculateDigitsFrom(int value, int digits);
}
