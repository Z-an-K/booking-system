package com.dxc.meetingroomreservationsystem.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    private static final Calendar calendar = Calendar.getInstance();
    public static LocalTime convertDate2LocalTime(Date date){
        calendar.setTime(date);
        int startHour = calendar.get(Calendar.HOUR_OF_DAY);
        int startMinute = calendar.get(Calendar.MINUTE);
        int startSecond = calendar.get(Calendar.SECOND);

        return LocalTime.of(startHour,startMinute,startSecond);
    }
}
