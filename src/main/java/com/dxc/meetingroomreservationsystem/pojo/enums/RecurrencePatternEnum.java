package com.dxc.meetingroomreservationsystem.pojo.enums;

public enum RecurrencePatternEnum {
    weekly(1),
    monthly(2);

    private int code;
    RecurrencePatternEnum(int code){
        this.code = code;
    }

    public int getPatternCode(){
        return code;
    }
}
