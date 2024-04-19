package com.dxc.meetingroomreservationsystem.pojo.enums;

public enum WeekTypeEnum {

    Day(0),
    first(1),
    second(2),
    third(3),
    forth(4),
    last(5);

    private int code;

    WeekTypeEnum(int code){
        this.code = code;
    }

    public int getWeekType(){
        return this.code;
    }
}
