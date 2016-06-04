LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := picalc
LOCAL_SRC_FILES := picalc/bpp.cpp
include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := picalc_test
LOCAL_SRC_FILES := picalc/bpp_test.cpp
LOCAL_SHARED_LIBRARIES := picalc
LOCAL_STATIC_LIBRARIES := googletest_main
include $(BUILD_EXECUTABLE)

$(call import-module,third_party/googletest)
