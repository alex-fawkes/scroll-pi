#include "fawkes/scrollpi/math/bbp/string.hpp"
#include "fawkes/scrollpi/math/floating.hpp"

#include <jni.h>

using namespace fawkes::scrollpi::math;

static jstring to_jstring(JNIEnv* const environment, const std::string& string) {
    return environment->NewStringUTF(string.c_str());
}

extern "C" {
JNIEXPORT jdouble JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeFloating_truncate(
        const JNIEnv* const,
        const jclass,
        const jdouble d,
        const jint digits) {
    return floating::trunc(d, digits);
}

JNIEXPORT jdouble JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeFloating_truncateFront(
        const JNIEnv* const,
        const jclass,
        const jdouble d,
        const jint digits) {
    return floating::trunc_front(d, digits);
}

JNIEXPORT jdouble JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeFloating_truncateBoth(
        const JNIEnv* const,
        const jclass,
        const jdouble d,
        const jint front_digits,
        const jint back_digits) {
    return floating::trunc_both(d, front_digits, back_digits);
}

JNIEXPORT jdouble JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeFloating_decimalShiftLeft(
        const JNIEnv* const,
        const jclass,
        const jdouble d,
        const jint digits) {
    return floating::decimal_shift_left(d, digits);
}

JNIEXPORT jdouble JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeFloating_decimalShiftRight(
        const JNIEnv* const,
        const jclass,
        const jdouble d,
        const jint digits) {
    return floating::decimal_shift_right(d, digits);
}

JNIEXPORT jstring JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeBbpAlgorithm_calculate(
        JNIEnv* const environment,
        const jclass,
        const jint digits) {
    return to_jstring(environment, bbp::string::calculate(digits));
}

JNIEXPORT jstring JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeBbpAlgorithm_calculateFrom(
        JNIEnv* const environment,
        const jclass,
        const jint n,
        const jint digits) {
    return to_jstring(environment, bbp::string::calculate_from(n, digits));
}

JNIEXPORT jstring JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeBbpAlgorithm_calculateDigitsFrom(
        JNIEnv* const environment,
        const jclass,
        const jint n,
        const jint digits) {
    return to_jstring(environment, bbp::string::calculate_digits_from(n, digits));
}
}
