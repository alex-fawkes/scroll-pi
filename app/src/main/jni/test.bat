@echo off

rem Android device needs to be connected to run tests.
set target=x86
adb push ../libs/%target%/libpicalc.so /data/local/tmp/
adb push ../libs/%target%/picalc_test /data/local/tmp/
adb shell chmod 775 /data/local/tmp/picalc_test
adb shell "LD_LIBRARY_PATH=/data/local/tmp /data/local/tmp/picalc_test"
