package com.dxc.meetingroomreservationsystem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dxc.meetingroomreservationsystem.configuration.mp.IntegerListTypeHandler;
import com.dxc.meetingroomreservationsystem.pojo.enums.RecurrencePatternEnum;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@TableName("tb_event")
public class Event extends BasePojo {
    private static final long serialVersionUID = 6455529227371581249L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private Date start;
    private Date end;
    private String backgroundColor;
    private String details;
    private boolean recurrence;
    private Integer recurrencePattern;
    private Date from;
    private Date to;
    @TableField(typeHandler = IntegerListTypeHandler.class)
    private List<Integer> week;
    private Integer weekth;
    private Integer weekType;

    public Event(){

    }
    public Event(EventBo eventBo){
        this.start = eventBo.getStartTime();
        this.end = eventBo.getEndTime();
        this.details = eventBo.getDetails();
        this.title = eventBo.getTitle();
    }
}
