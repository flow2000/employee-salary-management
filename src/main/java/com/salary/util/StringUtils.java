package com.salary.util;


import java.util.*;

public class StringUtils {
    private final static String toUpperStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final static String toLowerStr = "abcdefghijklmnopqrstuvwsyz";

    private final static String num = "0123456789";

    private final static String TYPE = "searchType";

    private final static String CONTENT = "searchContent";

    /**
     * 生成随机盐
     * @return 盐
     */
    public static String getSalt(){
        String salt = "";
        String str = toLowerStr+toUpperStr+num;
        for (int i = 0; i < 6; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            salt += ch;
        }
        return salt;
    }

    /**
     * 字符串转map数组
     * @param searchKey 关键字
     * @param searchValue 值
     * @return map数组
     */
    public static List<Map> strToMapList(String searchKey, String searchValue){
        List<Map> list = new ArrayList<>();
        if(isEmpty(searchKey)||isEmpty(searchValue)){
            return list;
        }
        String[] keyArray = searchKey.split(";");
        String[] valueArray = searchValue.split(";"); //字符串转数组
        for (int i = 0; i < keyArray.length && i<valueArray.length; i++) {
            Map<String,Object> map = new HashMap<>();
            if(!isEmpty(keyArray[i]) && !isEmpty(valueArray[i]) ){ //不为空
                map.put(TYPE,keyArray[i]);
                map.put(CONTENT,valueArray[i]);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 判断字符串是否为空，是则返回true，不是则返回false
     * @param str 字符串
     * @return 布尔值
     */
    public static boolean isEmpty(String str){
        return str==null||"".equals(str);
    }

    /**
     * map数组转字符串
     * @param list map数组
     * @param key 关键字
     * @return map数组
     */
    public static String[] mapListToStr(List<Map> list,String key) {
        String[] array = new String[list.size()];
        int index=0;
        for (int i = 0; i < list.size(); i++) {
            Map map = list.get(i);
            if(map.get(key)!=null){
                array[index]= map.get(key).toString();
                index++;
            }
        }
        return array;
    }

}
