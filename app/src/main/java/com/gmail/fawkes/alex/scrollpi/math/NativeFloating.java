package com.gmail.fawkes.alex.scrollpi.math;

class NativeFloating {
    static {
        System.loadLibrary("fawkes");
    }

    private NativeFloating() {
    }

    public static native double truncate(double d, int digits);

    public static native double truncateFront(double d, int digits);

    public static native double truncateBoth(double d, int front_digits, int back_digits);

    public static native double decimalShiftLeft(double d, int digits);

    public static native double decimalShiftRight(double d, int digits);
}
