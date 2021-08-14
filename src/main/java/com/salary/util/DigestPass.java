package com.salary.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * MD5消息摘要算法
 *
 * @author XIONG JIAJIA
 *
 * */

public class DigestPass {

    private MessageDigest messageDigest;
    private byte[] byteArgs = null;

    public String getDigestString(String originalText){
        try {

            //生成MessageDigest对象,传入所用算法的参数(MD5)
            messageDigest = MessageDigest.getInstance("MD5");
            //使用 getBytes( )方法生成字符串数组
            messageDigest.update(originalText.getBytes("GBK"));

            byteArgs = messageDigest.digest();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        } finally {
            messageDigest.reset();
        }

        //将结果转换成字符串 ; result清空，否则它会自动累加!
        StringBuilder resultStr = new StringBuilder();
        for (byte byteArg : byteArgs) {
            resultStr.append(Integer.
                    toHexString((0x000000ff & byteArg) | 0xffffff00)
                    .substring(6));
        }
        return resultStr.toString();
    }

}
