package com.dxc.meetingroomreservationsystem.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventDto extends Event{


    private static final long serialVersionUID = 43796057219575508L;

    private String username;

    private String roomId;

    private String location;
}
