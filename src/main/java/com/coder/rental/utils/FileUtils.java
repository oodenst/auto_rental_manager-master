package com.coder.rental.utils;

import cn.hutool.core.util.StrUtil;

import java.util.UUID;

public class FileUtils {
    public static String getFileExtension(String fileName) {
        return "."+ StrUtil.subAfter(fileName, ".", true);
    }
    public static String getFileName() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static String getFileName(String fileName) {
        return getFileName()+getFileExtension(fileName);
    }
}