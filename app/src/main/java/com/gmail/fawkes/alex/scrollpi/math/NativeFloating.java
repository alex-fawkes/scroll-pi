package com.gmail.fawkes.alex.scrollpi.math;

class NativeFloating {
    static {
        System.loadLibrary("fawkes");
    }

    private NativeFloating() {
    }

    public static native double truncate(double value, int digits);

    public static native double truncateFront(double value, int digits);

    public static native double truncateBoth(double value, int front_digits, int back_digits);

    public static native double decimalShiftLeft(double value, int digits);

    public static native double decimalShiftRight(double value, int digits);
}
