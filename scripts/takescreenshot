#!/bin/sh

echo '开始截图'
adb shell screencap -p /sdcard/screen.png

adb pull /sdcard/screen.png
echo '截图完成，存放到当前目录：screen.png'

adb shell rm /sdcard/screen.png

echo '正在打开：'
open screen.png