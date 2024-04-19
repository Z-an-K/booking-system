package com.dxc.meetingroomreservationsystem.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

@Data
public class BasePojo  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
