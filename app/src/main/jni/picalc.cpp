#include "picalc/bbp.hpp"

#include <jni.h>

// TODO: match c++ namespacing to java package structure
// TODO: save c++ in cpp directory like java
using namespace scratchapp::picalc::bbp;

static jstring to_jstring(JNIEnv* environment, const std::string& str) {
    return environment->NewStringUTF(str.c_str());
}

extern "C" {
    JNIEXPORT jstring JNICALL
    Java_net_ddns_adfawkes_scratchapp_NativeBbp_calculateTo(
            JNIEnv* environment, jclass, int digits) {
        return to_jstring(environment, calculate_to(digits));
    }

    JNIEXPORT jstring JNICALL
    Java_net_ddns_adfawkes_scratchapp_NativeBbp_calculateFrom(
            JNIEnv* environment, jclass, int n, int digits) {
        return to_jstring(environment, calculate_from(n, digits));
    }

    JNIEXPORT jstring JNICALL
    Java_net_ddns_adfawkes_scratchapp_NativeBbp_calculateDigitsFrom(
            JNIEnv* environment, jclass, int n, int digits) {
        return to_jstring(environment, calculate_digits_from(n, digits));
    }
}
