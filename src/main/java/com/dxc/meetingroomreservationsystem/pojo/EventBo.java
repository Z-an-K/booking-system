package com.dxc.meetingroomreservationsystem.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class EventBo implements Serializable {


    private static final long serialVersionUID = -5642251442406958109L;

    private String username;
    private String details;
    private Date startTime;
    private Date endTime;
    private String room;
    private String title;
    private boolean recurrence;
    private Integer recurrencePattern;
    private Date from;
    private Date to;
    private List<Integer> week;
    private Integer weekth;
    private Integer weekType;


}
