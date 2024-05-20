package com.dxc.meetingroomreservationsystem.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class EventDto extends Event{


    private static final long serialVersionUID = 43796057219575508L;

    private String username;

    private String roomId;

    private String location;

    private String backgroundColor;



}
