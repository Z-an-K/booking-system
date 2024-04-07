package com.dxc.meetingroomreservationsystem.pojo.enums;
public enum ResponseCode {

    // 系统默错误码
    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    PARAM_ERROR(2, "PARAM_ERROR"),
    NO_AUTH(3, "NO_SYSTEM_AUTH"),
    ERR_AUTH(4, "ILLEGAL_ARGUMENT"),
    ;

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}