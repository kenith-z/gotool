package xyz.hcworld.gotool.random;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机字符串生成工具
 * @ClassName: RandomStr
 * @Author: 张红尘
 * @Date: 2020/12/9 10:19
 * @Version： 1.0
 */
public class Random {
    /**
     * 小写字母字符串
     */
    private static final String LOWERCASE_STR = "abcdefghijklmnopqrstuvwxyz";
    /**
     * 数字字符串
     */
    private static final String NUMBER_STR = "0123456789";
    /**
     * 大写字母字符串
     */
    private static final String CAPITALS_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 特殊字符字符串
     */
    private static final String SYMBOL_STR = "~!@#$%^&*()-_=+[]{}|\\;:'\",<.>/?";
    /**
     * 生成指定位数的随机字符串
     * @param length 生成字符串的位数
     * @param str 从这个字符串中抽
     * @return 随机字符串
     */
    public static String random(int length,String str) {
        StringBuilder sb = new StringBuilder();
        int sLength = str.length();
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(threadLocalRandom.nextInt(sLength)));
        }
        return sb.toString();
    }

    /**
     * 生成小写字母随机字符串
     * @param length 生成字符串的长度
     * @return 全小写随机字符串
     */
    public static String lowercase(int length) {
        return random(length,LOWERCASE_STR);
    }
    /**
     * 生成大写字母随机字符串
     * @param length 生成字符串的长度
     * @return 全大写随机字符串
     */
    public static String capitals(int length) {
        return random(length,CAPITALS_STR);
    }

    /**
     * 生成数字随机字符串
     * @param length 生成字符串的长度
     * @return 全数字随机字符串
     */
    public static String number(int length) {
        return random(length,NUMBER_STR);
    }

    /**
     * 生成符号字符串
     * @param length 生成字符串的长度
     * @return 全符号随机字符串
     */
    public static String symbol(int length) {
        return random(length,SYMBOL_STR);
    }

    /**
     * 生成大小写字母加数字的随机字符串
     * @param length 生成字符串的长度
     * @return 大小写加数字混合随机字符串
     */
    public static String lettersAndNum(int length) {
        return random(length,LOWERCASE_STR+CAPITALS_STR+NUMBER_STR);
    }

    /**
     * 生成大小写字母加数字和特殊字符的随机字符串
     * @param length 生成字符串的长度
     * @return 大小写字母加数字和特殊字符混合随机字符串
     */
    public static String lettersAndNumAndSym(int length) {
        return random(length,LOWERCASE_STR+CAPITALS_STR+NUMBER_STR+SYMBOL_STR);
    }
    /**
     * 生成大小写字母加数字和自定义特殊字符的随机字符串
     * @param length 生成字符串的长度
     * @return 大小写字母加数字和自定义特殊字符混合随机字符串
     */
    public static String lettersAndNumAndSym(int length,String symbol) {
        return random(length,LOWERCASE_STR+CAPITALS_STR+NUMBER_STR+symbol);
    }
}
