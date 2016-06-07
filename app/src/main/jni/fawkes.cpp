#include "fawkes/scrollpi/math/bbp.hpp"

#include <jni.h>

using namespace fawkes::scrollpi::math;

static jstring to_jstring(JNIEnv* const environment, const std::string& string) {
    return environment->NewStringUTF(string.c_str());
}

extern "C" {
JNIEXPORT jstring JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeBbp_calculate(
        JNIEnv* const environment, const jclass, const int digits) {
    return to_jstring(environment, bbp::calculate(digits));
}

JNIEXPORT jstring JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeBbp_calculateFrom(
        JNIEnv* const environment, const jclass, const int n, const int digits) {
    return to_jstring(environment, bbp::calculate_from(n, digits));
}

JNIEXPORT jstring JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeBbp_calculateDigitsFrom(
        JNIEnv* const environment, const jclass, const int n, const int digits) {
    return to_jstring(environment, bbp::calculate_digits_from(n, digits));
}
}
