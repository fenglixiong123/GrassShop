package com.grass.common.utils.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fenglixiong on 2017/9/27.
 */
public class ArrayConvertUtils {

    @SuppressWarnings("all")
    public static String array2String(String[] arr) {
        String r = "";
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                r += ",";
            }
            r += arr[i];
        }
        return r;
    }

    public static String[] string2Array(String s) {
        return s.split(",");
    }

    @SuppressWarnings("all")
    public static String intArray2String(int[] arr) {
        String r = "";
        if (arr != null && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                if (i > 0) {
                    r += ",";
                }
                r += arr[i];
            }
        }
        return r;
    }

    public static String intArray2String(Integer[] arr) {
        String r = "";
        if (arr != null && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != null) {
                    if (i > 0) {
                        r += ",";
                    }
                    r += arr[i];
                }
            }
        }
        return r;
    }

    public static List<Integer> intArray2List(int[] arr) {
        List<Integer> list = new ArrayList<Integer>();
        for (int k : arr) {
            list.add(k);
        }
        return list;
    }

    public static String[] intArray2StringArr(int[] ss) {
        String[] r = new String[ss.length];
        for (int i = 0; i < ss.length; i++) {
            r[i] = String.valueOf(ss[i]);
        }
        return r;
    }

    public static int[] string2IntArray(String s) {
        if (s != null) {
            String[] ss = s.replaceAll(";", ",").
                    replaceAll("/", ",").
                    replaceAll("，", ",").
                    replaceAll("；", ",").
                    replaceAll("\\s+", ",").
                    split(",");
            List<Integer> ll = new ArrayList<Integer>();
            for (String i : ss) {
                if (i.trim().length() > 0) {
                    int k = Integer.parseInt(i);
                    if (!intInList(ll, k))
                        ll.add(k);
                }
            }
            return list2Array(ll);
        }
        return new int[]{};
    }

    public static int[] list2Array(List<Integer> ll) {
        int[] arr = new int[ll.size()];
        for (int i = 0; i < ll.size(); i++) {
            arr[i] = ll.get(i);
        }
        return arr;
    }

    public static String[] listStr2Array(List<String> ll) {
        String[] arr = new String[ll.size()];
        for (int i = 0; i < ll.size(); i++) {
            arr[i] = ll.get(i);
        }
        return arr;
    }

    public static boolean intInList(List<Integer> ll, int t) {
        for (Integer i : ll) {
            if (i == t) {
                return true;
            }
        }
        return false;
    }

}
