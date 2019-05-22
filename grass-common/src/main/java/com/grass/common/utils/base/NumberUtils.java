package com.grass.common.utils.base;

/**
 * @Author Fenglixiong
 * @Create 2019/5/22 23:13
 * @Description
 **/
public class NumberUtils {

    //-----------比较对象-----------------------------------------------------------

    @SuppressWarnings("all")
    public static int compareNullObject(Object o1, Object o2) {
        if (o1 != null && o2 == null) return 1;
        else if (o1 == null && o2 != null) return -1;
        else return 0;
    }

    @SuppressWarnings("all")
    public static int compareNullable(Double d1, Double d2) {
        if (d1 != null && d2 != null) {
            return d1.compareTo(d2);
        } else if (d1 != null && d2 == null) {
            return 1;
        } else if (d2 != null && d1 == null) {
            return -1;
        } else {
            return 0;
        }
    }

    @SuppressWarnings("all")
    public static int compareNullable(Integer d1, Integer d2) {
        if (d1 != null && d2 != null) {
            return d1.compareTo(d2);
        } else if (d1 != null && d2 == null) {
            return 1;
        } else if (d2 != null && d1 == null) {
            return -1;
        } else {
            return 0;
        }
    }

    @SuppressWarnings("all")
    public static int compareNullable(Number d1, Number d2) {
        if (d1 != null && d2 != null) {
            return ((Double) d1).compareTo((Double) d2);
        } else if (d1 != null && d2 == null) {
            return 1;
        } else if (d2 != null && d1 == null) {
            return -1;
        } else {
            return 0;
        }
    }

}
