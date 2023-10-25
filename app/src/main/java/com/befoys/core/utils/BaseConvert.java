package com.befoys.core.utils;

import java.text.DecimalFormat;

public class BaseConvert {
    public static String toPersian (Float number) {
        String num = number.toString();
        return toPersian(num);
    }

    public static String toPersian (Integer number) {
        String num = number.toString();
        return toPersian(num);
    }

    public static String toCurrency(Long amount)
    {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public static String toCurrency(Float amount)
    {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public static String toCurrency(Integer amount)
    {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);
    }

    public static String toPersian (String num) {
        if (num != null) {
            return num
                    .replace("0", "۰")
                    .replace("1", "۱")
                    .replace("2", "۲")
                    .replace("3", "۳")
                    .replace("4", "۴")
                    .replace("5", "۵")
                    .replace("6", "۶")
                    .replace("7", "۷")
                    .replace("8", "۸")
                    .replace("9", "۹");
        }
        return  "";
    }

    public static String toEnglish(String num) {
        if (num != null) {
            return num
                    .replace("۰", "0")
                    .replace("۱", "1")
                    .replace("۲", "2")
                    .replace("۳", "3")
                    .replace("۴", "4")
                    .replace("۵", "5")
                    .replace("۶", "6")
                    .replace("۷", "7")
                    .replace("۸", "8")
                    .replace("۹", "9");
        }
        return "";
    }
}
