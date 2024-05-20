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
import java.util.Objects;

@Data
@TableName("tb_event")
public class Event extends BasePojo {
    private static final long serialVersionUID = 6455529227371581249L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    @TableField("`start`")
    private Date start;
    @TableField("`end`")
    private Date end;
    private String details;
    private boolean recurrence;
    private Integer recurrencePattern;
    @TableField("`from`")
    private Date from;
    @TableField("`to`")
    private Date to;
    @TableField(value = "`week`",typeHandler = IntegerListTypeHandler.class)
    private List<Integer> week;
    private Integer weekth;
    private Integer weekType;
    private String groupId;

    public Event(){

    }
    public Event(EventBo eventBo){
        this.start = eventBo.getStartTime();
        this.end = eventBo.getEndTime();
        this.details = eventBo.getDetails();
        this.title = eventBo.getTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(groupId, event.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }
}
