package com.coder.rental.utils;

import cn.hutool.extra.pinyin.PinyinUtil;
/**
 * 将文字首字母转换为拼音大写
 */
public class PinYinUtils {
    public static String getPinYin(String str) {
        return PinyinUtil.getFirstLetter(str, "").toUpperCase();
    }
    public static void main(String[] args) {
        System.out.println(getPinYin("你好")); // 输出：H
    }

}
