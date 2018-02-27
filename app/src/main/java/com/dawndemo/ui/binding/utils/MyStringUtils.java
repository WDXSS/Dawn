package com.dawndemo.ui.binding.utils;

/**
 * Created by zc on 2018/2/27.
 */

public class MyStringUtils {

    public static String capitalize(final String word) {
        if(word == null){
            return "";
        }
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }
}
