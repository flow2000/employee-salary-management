package com.salary.util;


import java.util.Random;

public class StringUtils {
    private final static String toUpperStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final static String toLowerStr = "abcdefghijklmnopqrstuvwsyz";

    private final static String num = "0123456789";

    /**
     * 生成随机盐
     * @return 盐
     */
    public static String getSalt(){
        String salt = new String();
        String str = toLowerStr+toUpperStr+num;
        for (int i = 0; i < 6; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            salt += ch;
        }
        return salt;
    }

}
