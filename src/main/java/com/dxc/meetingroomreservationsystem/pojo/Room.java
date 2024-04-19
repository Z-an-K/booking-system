package com.dxc.meetingroomreservationsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("tb_meetingroom")
public class Room extends BasePojo implements Serializable {

    private static final long serialVersionUID = -3015785655150982949L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String location;
    private String roomId;
    private Boolean status;
    private String equipment;
    private Integer seat;
    private String color;
}
