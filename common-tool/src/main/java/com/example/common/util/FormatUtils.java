package com.example.common.util;

import java.text.DecimalFormat;

public class FormatUtils {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");
    public static String format(double number) {
        return String .format("%.2f",number);
    }

    public static double formatDouble(double number) {
        String value = format(number);
        return Double.parseDouble(value);
    }
}
