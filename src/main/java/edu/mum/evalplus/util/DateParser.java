package edu.mum.evalplus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static Date parse(String value) {
        SimpleDateFormat formatter1 = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            return formatter2.parse(formatter2.format(formatter1.parse(value)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
