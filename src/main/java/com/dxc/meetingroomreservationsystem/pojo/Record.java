package com.dxc.meetingroomreservationsystem.pojo;

import lombok.Data;

@Data
public class Record {
    private int userId;
    private int eventId;
    private int roomId;

    public Record(){}

    public Record(int userId, int eventId, int roomId) {
        this.userId = userId;
        this.eventId = eventId;
        this.roomId = roomId;
    }
}
