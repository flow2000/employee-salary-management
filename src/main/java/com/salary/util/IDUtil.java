package com.salary.util;

import java.util.Random;

/**
 * id生成类
 */
public class IDUtil {

    private static final String str = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 生成业务随机编号
     * @param prefix 业务编号
     * @return 编号
     */
    public static String geneId(String prefix){
        return prefix+ System.currentTimeMillis() + new Random().nextInt(900)+100;
    }

    /**
     * 生成员工编号
     * @return 员工编号
     */
    public static String getVariableNum() {
        int t = new Random().nextInt(9999999);
        while (t<1000000){
            t = new Random().nextInt(9999999);
        }
        return ""+t;
    }

    /**
     * 获取六位随机字符串类型的数字
     * @return 六位随机字符串类型的数字
     */
    public static String getSixNum() {
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 6; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

}
