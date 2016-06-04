#include <jni.h>

jstring Java_net_ddns_adfawkes_scratchapp_MainActivity_stringFromJNI2(JNIEnv* env) {
    return (*env)->NewStringUTF(env, "hello world.");
}
