package com.gj.gejigeji.util;

import java.util.Arrays;

public class StringUtil {

    public static String compareable(String first,String last){
        StringBuilder sb = new StringBuilder();
        String temp = first + last;
        char[] chars = temp.toCharArray();
        Arrays.sort(chars);
        for (char aChar : chars) {
            sb.append(aChar);
        }
        return sb.toString();
    }
}
