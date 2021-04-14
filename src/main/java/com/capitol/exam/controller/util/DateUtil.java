package com.capitol.exam.controller.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").parse(date);
        } catch (Exception e) {
            return null;
        }
    }

}
