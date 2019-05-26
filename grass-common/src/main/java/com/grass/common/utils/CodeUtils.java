package com.grass.common.utils;

import java.util.Random;

/**
 * @Author Fenglixiong
 * @Create 2019/5/22 23:06
 * @Description
 **/
public class CodeUtils {

    public static int getRangeNumber(int min,int max){
        if(min<0){min = 0;}
        if(max<0){max = 0;}
        if(min>=max){min = 0;max = 10;}
        Random generator = new Random();
        return generator.nextInt(max-min+1)+min;
    }

    /**
     * 生成随机代码
     * @param length
     * @return
     */
    public static String getRandomCode(int length) {
        Random generator = new Random();
        char[] cs = new char[length];
        for (int i = 0; i < cs.length; i++) {
            int characterType = generator.nextInt(3);
            int k = 0;
            if (characterType == 0)
                k = 49 + generator.nextInt(9);
            else if (characterType == 1)
                k = 65 + generator.nextInt(26);
            else if (characterType == 2)
                k = 97 + generator.nextInt(26);
            cs[i] = (char) k;
        }
        return new String(cs);
    }

    /**
     * 生成随机数
     * @param length
     * @return
     */
    public static String getRandomNumber(int length) {
        Random generator = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int k = generator.nextInt(9);
            sb.append(k);
        }
        return sb.toString();
    }

    public static String getNumberCode(int length){
        String baseString = "1234567890";
        return generateCode(length,baseString);
    }

    public static String getStringCode(int length){
        String baseString = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ";
        return generateCode(length,baseString);
    }

    public static String getSomeCode(int length){
        String baseString = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ23456789";
        return generateCode(length,baseString);
    }

    /**
     * 只有数字的密码生成
     *
     * @param length
     * @return
     */
    private static String generateCode(int length,String baseSeed) {
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int te;
        for (int i = 1; i <= length; i++) {
            te = r.nextInt(baseSeed.length());
            sb.append(baseSeed.charAt(te));
        }
        return sb.toString();
    }

}
