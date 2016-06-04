#include "picalc/bpp.hpp"

#include <jni.h>

using namespace scratchapp::picalc::bpp;

static jstring to_jstring(JNIEnv* environment, const std::string& str) {
    return environment->NewStringUTF(str.c_str());
}

extern "C" {
    JNIEXPORT jstring JNICALL
    Java_net_ddns_adfawkes_scratchapp_NativeBppCalc_calculateTo(
            JNIEnv* environment, jclass, int digits) {
        return to_jstring(environment, calculate_to(digits));
    }

    JNIEXPORT jstring JNICALL
    Java_net_ddns_adfawkes_scratchapp_NativeBppCalc_calculateFrom(
            JNIEnv* environment, jclass, int n, int digits) {
        return to_jstring(environment, calculate_from(n, digits));
    }

    JNIEXPORT jstring JNICALL
    Java_net_ddns_adfawkes_scratchapp_NativeBppCalc_calculateDigitsFrom(
            JNIEnv* environment, jclass, int n, int digits) {
        return to_jstring(environment, calculate_digits_from(n, digits));
    }
}
