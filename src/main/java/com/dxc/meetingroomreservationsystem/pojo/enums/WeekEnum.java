package com.dxc.meetingroomreservationsystem.pojo.enums;

public enum WeekEnum {

    workDay(0),
    mon(1),
    tues(2),
    wed(3),
    thur(4),
    fri(5),
    sat(6),
    sun(7);
    
    private int weekCode;
    
    WeekEnum(int code){
        this.weekCode = code;
    }
    
    public int getWeekCode(){
        return weekCode;
    }
}
