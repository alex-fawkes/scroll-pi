#include "fawkes/scrollpi/math/bbp.hpp"

#include <jni.h>

// TODO: match c++ namespacing to java package structure
// TODO: save c++ in cpp directory like java
using namespace fawkes::scrollpi::math;

static jstring to_jstring(JNIEnv* environment, const std::string &str) {
    return environment->NewStringUTF(str.c_str());
}

extern "C" {
JNIEXPORT jstring JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeBbp_calculateTo(
        JNIEnv* environment, jclass, int digits) {
    return to_jstring(environment, bbp::calculate_to(digits));
}

JNIEXPORT jstring JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeBbp_calculateFrom(
        JNIEnv* environment, jclass, int n, int digits) {
    return to_jstring(environment, bbp::calculate_from(n, digits));
}

JNIEXPORT jstring JNICALL
Java_com_gmail_fawkes_alex_scrollpi_math_NativeBbp_calculateDigitsFrom(
        JNIEnv* environment, jclass, int n, int digits) {
    return to_jstring(environment, bbp::calculate_digits_from(n, digits));
}
}
